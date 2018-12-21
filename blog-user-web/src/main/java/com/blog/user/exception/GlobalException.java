package com.blog.user.exception;

import com.blog.common.constant.Constants;
import com.blog.common.result.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 2018/10/22.
 */
@RestControllerAdvice
@ResponseBody
public class GlobalException {

    private Logger logger= LogManager.getLogger(GlobalException.class);
    @ExceptionHandler
    public Response handler(Exception exception)throws Exception{
        Response response=new Response();
        if(exception.getMessage().equals(Constants.MISS_TOKEN)){
            return Response.needLogin();
        }
        return response.setCode(Response.FAILURE).setMsg(exception.getMessage());

    }
}
