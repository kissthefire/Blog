package com.blog.redis.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.blog.redis.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dell on 2018/7/18.
 */
@Service
@Component
public class JedisClientSingle implements JedisClient {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取值
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置值
     * @param key
     * @param value
     */
    @Override
    public void set(String key, Object value) {
        if(value instanceof String){
            String obj=(String)value;
            stringRedisTemplate.opsForValue().set(key,obj);
        }else if(value instanceof List){
            List obj=(List)value;
            stringRedisTemplate.opsForList().leftPushAll(key,obj);
        }else if(value instanceof Map){
            Map obj=(Map)value;
            stringRedisTemplate.opsForHash().putAll(key,obj);
        }
    }

    /**
     * 设置值并设置超时时间
     * @param key
     * @param value
     * @param timeout
     */
    @Override
    public void setAndExpire(String key, Object value, Long timeout) {
        if(value instanceof String){
            String obj=(String)value;
            stringRedisTemplate.opsForValue().set(key,obj);
        }else if(value instanceof List){
            List obj=(List)value;
            stringRedisTemplate.opsForList().leftPushAll(key,obj);
        }else if(value instanceof Map){
            Map obj=(Map)value;
            stringRedisTemplate.opsForHash().putAll(key,obj);
        }
        if(timeout!=null){
            stringRedisTemplate.expire(key,timeout,TimeUnit.SECONDS);
        }
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return
     */
    @Override
    public long incr(String key,long delta) {
        if(delta<0){
            throw  new RuntimeException("递增因子必须大于0");
        }
        return stringRedisTemplate.opsForValue().increment(key,delta);
    }

    /**
     * 设置超时时间
     * @param key
     * @param timeout
     */
    @Override
    public void expire(String key,Long timeout){
        stringRedisTemplate.expire(key,timeout,TimeUnit.SECONDS);
    }

    @Override
    public long ttl(String key) {
        return 0;
    }

    @Override
    public boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

}
