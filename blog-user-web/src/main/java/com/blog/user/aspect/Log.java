package com.blog.user.aspect;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by Dell on 2018/10/27.
 */
public class Log {
    private String requestPath=null; //请求地址
    private String method=null; //请求方法
    private Map<?,?> inputParamMap=null;  //输入参数
    private String paramStr="";  //输入参数字符串
    private Map<String,Object> outputParamMap=null;
    private Object returned;  //返回值
    private String clazz;  //类全名
    private String clientIp;    //客户端ip
    private long startTimeMillis=0;  //开始时间
    private long endTimeMillis=0;    //结束时间

    public String getRequestPath() {
        if(requestPath==null){
            return "";
        }
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getMethod() {
        if(method==null){
            return "";
        }
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<?, ?> getInputParamMap() {
        return inputParamMap;
    }

    public void setInputParamMap(Map<?, ?> inputParamMap) {
        this.inputParamMap = inputParamMap;
    }

    public String getParamStr() {
        if(paramStr==null){
            return "";
        }
        return paramStr;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }

    public Map<String, Object> getOutputParamMap() {
        return outputParamMap;
    }

    public void setOutputParamMap(Map<String, Object> outputParamMap) {
        this.outputParamMap = outputParamMap;
    }

    public Object getReturned() {
        return returned;
    }

    public void setReturned(Object returned) {
        this.returned = returned;
    }

    public String getClazz() {
        if(clazz==null){
            return "";
        }
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getClientIp() {
        if(clientIp==null){
            return "";
        }
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    public void setStartTimeMillis(long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }

    public long getEndTimeMillis() {
        return endTimeMillis;
    }

    public void setEndTimeMillis(long endTimeMillis) {
        this.endTimeMillis = endTimeMillis;
    }

    @Override
    public String toString() {
        long diffTime=endTimeMillis-startTimeMillis;
        //方法执行时间
        String execTime=diffTime+"ms";
        //目标方法开始执行时间
        String invokeTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);

        String returnStr= JSON.toJSONString(returned);


        StringBuffer execBuffer=new StringBuffer();
        execBuffer.append("\r\n");
        execBuffer.append("==================请求开始==================");
        execBuffer.append("\r\n");
        execBuffer.append("请求地址：");
        execBuffer.append(requestPath);

        execBuffer.append("\r\n");
        execBuffer.append("请求方法：");
        execBuffer.append(method);

        execBuffer.append("\r\n");
        execBuffer.append("全类名：");
        execBuffer.append(clazz);

        execBuffer.append("\r\n");
        execBuffer.append("请求IP：");
        execBuffer.append(clientIp);

        execBuffer.append("\r\n");
        execBuffer.append("请求用时：");
        execBuffer.append(execTime);

        execBuffer.append("\r\n");
        execBuffer.append("请求时间：");
        execBuffer.append(invokeTime);

        execBuffer.append("\r\n");
        execBuffer.append("输入参数：");
        execBuffer.append(paramStr);

        execBuffer.append("\r\n");
        execBuffer.append("返回参数：");
        execBuffer.append(returnStr);

        execBuffer.append("\r\n");
        execBuffer.append("==================请求结束==================");
        execBuffer.append("\r\n");
        execBuffer.append("\r\n");

        return execBuffer.toString();
    }
}
