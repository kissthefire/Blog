package com.blog.common.utils;

import java.util.Random;

/**
 * Created by Dell on 2018/10/10.
 */
public class VerifyCodeUtils {

    public static  final  char[] chars={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h',
    'i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    /**
     * 生成6位字母数字混合的验证码
     * @return
     */
    public static String genVerifyCode(){
        StringBuffer verifyCode=new StringBuffer();
        Random random=new Random();
        for(int i=0;i<=5;i++){
            verifyCode.append(chars[random.nextInt(chars.length)]);
        }
        return verifyCode.toString();
    }
}