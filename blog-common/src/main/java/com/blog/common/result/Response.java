package com.blog.common.result;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Dell on 2018/8/19.
 */
public class Response implements Serializable{

    public static Integer SUCCESS=200;  //请求成功

    public static Integer FAILURE=400;  //请求地址错误

    public static Integer DATAERROR=450;  //  数据不正确

    public static Integer ERROR=500;  //接口出错

    public static Integer NO_ACCESS=440;

    private Integer code;
    private Object data;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public Response setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Response(){
    }

    public Response(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //请求成功，只返回成功提示
    public static Response success(){
        return new Response(Response.SUCCESS,"ok");
    }

    //请求成功，返回成功数据
    public static Response successResult(Object data){
        return new Response(Response.SUCCESS,data,"ok");
    }

    /**
     * 请求失败，返回错误信息
     * @param errMsg
     * @return
     */
    public static Response error(String errMsg){
        return new Response(Response.ERROR,errMsg,"☹,似乎出了点问题(^_^)");
    }

    /**
     * 数据格式不正确
     * @param dataErrMsg
     * @return
     */
    public static Response dataError(String dataErrMsg){
        return new Response(Response.DATAERROR,null,dataErrMsg);
    }

    public static Response needLogin(){
        return new Response(Response.ERROR,NO_ACCESS,"请登录后访问^_^");
    }
}
