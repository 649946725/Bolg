package com.qyzmode.service;


import com.qyzmode.prjo.Type;

import java.util.List;

public interface TypeService {

    //增加Type
    int insertType(Type type);
    //通过id查询Type
    Type findTypeById(long id);
    //通过id查询Type
    Type findTypeByName(String name);
    //分页查询Type
    List<Type> selectType();
    //更改Type
    int updateType(Type type);
    //删除Type
    int deleteType(long id);
}
