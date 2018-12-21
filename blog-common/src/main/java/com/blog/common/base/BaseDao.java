package com.blog.common.base;

import java.util.List;

/**
 * Created by Dell on 2018/7/31.
 */
public interface BaseDao<T> {

    List<T> findAll();

    T findById(Long id);

    Integer save(T t);

    Integer update(T t);

}