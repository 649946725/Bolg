package com.qyzmode.dao;


import com.qyzmode.prjo.Type;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {
    //添加Type
    int insertType(@Param("type") Type type);
    //通过id查询Type
    Type findTypeById(long id);
    //通过name查询Type
    Type findTypeByName(String name);
    //分页查询Type
    List<Type> selectType();
    //更改Type
    int updateType(@Param("type") Type type);
    //删除Type
    int deleteType(long id);
}
