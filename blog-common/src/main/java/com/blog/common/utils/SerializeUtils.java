package com.blog.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by Dell on 2018/10/21.
 */
public class SerializeUtils {
    private static Logger logger= LoggerFactory.getLogger(Serializable.class);

    /**
     * 序列化一个对象
     * @param obj
     * @return
     */
    public static byte[] objToSerialize(Object obj){
        ObjectOutputStream oos=null;
        ByteArrayOutputStream byteOut=null;
        try {
            byteOut=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(byteOut);
            oos.writeObject(obj);
            byte[] bytes=byteOut.toByteArray();
            return bytes;
        }catch (Exception e){
            logger.error("对象序列化失败");
        }
        return null;
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public static Object deSerialize(byte[] bytes){
        ByteArrayInputStream in=null;
        try{
            in=new ByteArrayInputStream(bytes);
            ObjectInputStream objIn=new ObjectInputStream(in);
            return objIn.readObject();
        }catch (Exception e){
            logger.error("反序列化失败");
        }
        return null;
    }
}
