package com.blog.common.converter;

/**
 * Created by Dell on 2018/10/7.
 */
public class TypeConvertUtils {

    /**
     * 其他类型数据转成String类型
     * @param obj
     * @return
     */
    public static String toString(Object obj){
        return obj==null?null:obj.toString();
    }

    /**
     * 其他类型的数据转成Long类型
     * @param obj
     * @return
     */
    public static Long toLong(Object obj){
        return obj==null?null:Long.parseLong(TypeConvertUtils.toString(obj));
    }
}
