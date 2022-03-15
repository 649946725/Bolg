package com.qyzmode.service.Imp;

import com.qyzmode.dao.UserDao;
import com.qyzmode.prjo.User;
import com.qyzmode.service.UserService;
import com.qyzmode.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {

        return userDao.findByUsernameAndPassword(username, MD5Util.encodeByMD5(password));

    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }
}