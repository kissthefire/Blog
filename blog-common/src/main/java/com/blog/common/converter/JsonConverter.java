package com.blog.common.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2018/10/21.
 */
public class JsonConverter {

    private static Logger logger= LoggerFactory.getLogger(JsonConverter.class);

    //空值配置策略
    private static final SerializerFeature[] featuresWithNullValue = { SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty };

    /**
     * 将json字符串转化为实体对象
     * @param data Json字符串
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertJsonToObject(String data,Class<T> clazz){
        try{
            T t= JSON.parseObject(data,clazz);
            return t;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * JSONObject对象转换为实体对象
     * @param jsonObject
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertJSONObjToObject(JSONObject jsonObject,Class<T> clazz){
        try{
            T t=JSONObject.toJavaObject(jsonObject,clazz);
            return t;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将Json字符串数组转换为List集合对象
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJsonToArray(String data,Class<T> clazz){
        try{
            List<T> list=JSON.parseArray(data,clazz);
            return list;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将List<JSONObject>转换成List<T>集合对象
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJSONObjectArrayToArray(List<JSONObject> data,Class<T> clazz){
        try{
            List<T> list=new ArrayList<T>();
            data.forEach(jsonObj->{
                list.add(convertJSONObjToObject(jsonObj,clazz));
            });
            return list;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将对象转换为JSON字符串
     * @param obj
     * @return
     */
    public static  String convertObjToJson(Object obj){
        try{
            return JSON.toJSONString(obj);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将对象转为(JSON字符串)
     * @param obj
     * @return
     */
    public static String convertObjectToJSONBracket(Object obj){
        try{
            return "("+JSON.toJSONString(obj)+")";
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将对象转换成JSON对象
     * @param obj
     * @return
     */
    public static JSONObject convertObjectToJSONObject(Object obj){
        try{
            return (JSONObject)JSONObject.toJSON(obj);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将对象转换成包含默认值的JSON字符串
     * @param obj
     * @return
     */
    public static String convertObjectToJSONWithNullValue(Object obj){
        try{
            return JSON.toJSONString(obj,featuresWithNullValue);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
}
