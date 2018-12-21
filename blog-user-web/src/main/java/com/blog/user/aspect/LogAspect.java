package com.blog.user.aspect;

import com.alibaba.fastjson.JSONObject;
import com.blog.common.result.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Dell on 2018/10/22.
 */
@Aspect
@Configuration
public class LogAspect {
    private static final Logger logger= LogManager.getLogger(LogAspect.class);

    private Log log=null;

    @Pointcut("execution(* com.blog.*.controller..*.*(..))")
    public void controllerLog(){}

    /**
     * 前置通知，目标方法执行前运行
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint){
        if(log==null){
            log=new Log();
        }
        log.setStartTimeMillis(System.currentTimeMillis());
    }

    /**
     * 后置通知，目标方法执行后运行
     */
    @After("controllerLog()")
    public void doAfter(){
        log.setEndTimeMillis(System.currentTimeMillis());
        if(logger.isDebugEnabled()){
            logger.debug(log.toString());
        }
    }

    /**
     * 环绕通知
     * @param proceedingJoinPoint
     * @return
     */
    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint){
        if(log==null){
            log=new Log();
        }
        Response response=new Response();

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequest= (ServletRequestAttributes)requestAttributes;
        HttpServletRequest request=servletRequest.getRequest();
        String remoteAddr=request.getRemoteHost();
        log.setClientIp(remoteAddr);
        log.setRequestPath(request.getRequestURI());
        //设置请求方式
        log.setMethod(request.getMethod());
        //设置请求参数
        log.setInputParamMap(request.getParameterMap());

        //post请求参数转成字符串
        if("post".equalsIgnoreCase(request.getMethod())){
            Object[] requestParam=proceedingJoinPoint.getArgs();
            log.setParamStr(argsArrayToString(requestParam));
        }else{
            log.setParamStr(log.getInputParamMap().toString());
            log.setInputParamMap((Map<?,?>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            if(log.getInputParamMap()==null || log.getInputParamMap().size()==0){
                log.setParamStr(request.getQueryString());
            }else{
                log.setParamStr(log.getInputParamMap().toString());
            }
        }
            if(null!=log.getClientIp() && log.getClientIp().equals("127.0.0.1")){
                log.setClientIp(request.getHeader("X-Real-IP"));
            }

            Object result=new Object();
            try{
                result=proceedingJoinPoint.proceed(); //获取目标方法的返回值
            }catch (DuplicateKeyException ex){
                ex.printStackTrace();
                String msg=ex.getCause().getMessage().split("'")[1];
                response.setMsg("("+msg+")不能重复添加!");
                response.setCode(Response.DATAERROR);
                result=response;
            }catch (Throwable e){
                e.printStackTrace();
                response.setMsg("☹,似乎出了点问题(^_^)");
                result=response;
            }

            if(result instanceof ResponseEntity){
                log.setReturned("file");
            }else{
                log.setReturned(result);
            }

        Signature signature=proceedingJoinPoint.getSignature();
        MethodSignature methodSignature=(MethodSignature)signature;
        Object target=proceedingJoinPoint.getTarget();
        try{
            Method method=target.getClass().getMethod(methodSignature.getName(),methodSignature.getParameterTypes());
            Class clazz= method.getClass();
            log.setClazz(clazz.getName());
        }catch (NoSuchMethodException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public String argsArrayToString(Object[] paramArray){
        StringBuffer params=new StringBuffer("");
        if(paramArray!=null && paramArray.length>0){
            for(Object param:paramArray){
                if(param.getClass().getName().startsWith("com.blog")){
                    try{
                        Object jsonObj= JSONObject.toJSON(param);
                        params.append(jsonObj.toString());
                        params.append(" ");
                    }catch (Exception ex){
                        logger.error(ex);
                    }
                }
            }
        }
        return params.toString().trim();
    }
}
