package com.blog.user.validate;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2018/10/8.
 * 字段校验
 */
public class Validator {
    public static String validate(BindingResult result){
        List<String> errList=new ArrayList<>();
        for(FieldError error:result.getFieldErrors()){
            errList.add(error.getDefaultMessage());
        }
        if(CollectionUtils.isNotEmpty(errList)){
            return errList.get(0);
        }
        return null;
    }
}
