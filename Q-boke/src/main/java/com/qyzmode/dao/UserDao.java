package com.qyzmode.dao;

import com.qyzmode.prjo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    //根据用户名和密码查询用户
    User findByUsernameAndPassword(String username,String password);

    User findById(Long id);
}
