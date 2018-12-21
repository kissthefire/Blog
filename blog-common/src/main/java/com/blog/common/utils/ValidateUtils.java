package com.blog.common.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * 版本         开发者               创建日期
 * 1.0.0   InetCommunity(^_^)    on 2018/11/3.
 */
public class ValidateUtils {
    public static String validator(BindingResult result){
        List<String> errList=new ArrayList<String>();
        for(FieldError error:result.getFieldErrors()) {
            errList.add(error.getDefaultMessage());
        }
        if(errList!=null && errList.size()>0){
            return errList.get(0);
        }
        return null;
    }
}
