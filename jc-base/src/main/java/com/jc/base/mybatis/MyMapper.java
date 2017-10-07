package com.jc.base.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * Created by jasonzhu on 2016/12/22.
 */
public interface MyMapper<T> extends Mapper<T>,InsertListMapper<T> {
}
