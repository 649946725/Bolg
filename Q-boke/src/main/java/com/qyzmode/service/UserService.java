package com.qyzmode.service;

import com.qyzmode.prjo.User;


public interface UserService {


    User checkUser(String username, String password);


    User findById(Long id);

}
