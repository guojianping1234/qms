package com.myspringbt.demo.service;

import com.myspringbt.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<Map<String, Object>> getAllUser() {
        return userDao.getAllUser();
    }

    public void sendEmail(String emamil) {
    }

    public List<Map<String, Object>> searchname(String name) {
        List<Map<String, Object>> searchname = userDao.searchname(name);
        return searchname;
    }
}
