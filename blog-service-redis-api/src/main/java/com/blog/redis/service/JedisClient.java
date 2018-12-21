package com.blog.redis.service;

/**
 * lisx
 * Created by Dell on 2018/7/18.
 */
public interface JedisClient {
    Object get(String key);

    void set(String key, Object value);

    void setAndExpire(String key,Object value,Long second);

    long incr(String key,long delta);

    void expire(String key,Long timeout);

    long ttl(String key);

    boolean delete(String key);

    boolean hasKey(String key);

}
