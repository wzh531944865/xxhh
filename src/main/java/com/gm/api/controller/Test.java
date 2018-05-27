package com.gm.api.controller;

import com.gm.api.dao.UserDao;
import com.gm.api.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping()
public class Test {
    @Resource
    UserDao userDao;

//
//    @RequestMapping("/test")
//    public String test(){
//        return userDao.Aa();
//    }

    @RequestMapping("/test2")
    public UserInfo test2(){

        UserInfo userInfo = userDao.query();
        return userInfo;
    }

}
