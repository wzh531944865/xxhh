package com.gm.api.service;

import com.gm.api.dao.UserDao;
import com.gm.api.dao.UserPasswordDao;
import com.gm.api.vo.UserInfo;
import com.gm.api.vo.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by xxhh on 2018/5/27.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPasswordDao userPasswordDao;

    public UserInfo login(String userId) {
        String userToken = createUserToken();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserToken(userToken);
        userDao.updateUserToken(userInfo);
        return userDao.queryByUserId(userId);

    }

    public UserInfo queryUserInfoById(String userId) {
        return userDao.queryByUserId(userId);
    }

    public int register(String nickName, String password, String email, String mobileNo) {
        String userId = createUserId();

        createAccount(userId, nickName, email, mobileNo);
        createUserPassword(userId, password);
        return 1;//todo


    }

    public boolean checkUserPassword(String userId, String password) { //todo 修改为非明文
        UserPassword userPassword = userPasswordDao.queryByUserId(userId);
        return userPassword != null && userPassword.getPassword().equals(password);
    }

    public boolean checkUserToken(String userId, String userToken) {
        UserInfo userInfo = userDao.queryByUserId(userId);
        return userInfo != null && userInfo.getUserToken().equals(userToken);
    }

    private int createAccount(String userId, String nickName, String email, String mobileNo) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setNickName(nickName);
        userInfo.setEmail(email);
        userInfo.setMobileNo(mobileNo);
        return userDao.insertUserInfo(userInfo);
    }

    private int createUserPassword(String userId, String password) {
        UserPassword userPassword = new UserPassword();
        userPassword.setUserId(userId);
        userPassword.setPassword(password);
        return userPasswordDao.inserUserPassword(userPassword);

    }

    private String createUserId() {
        String userId = Long.toString(new Date().getTime());
        return userId;
    }

    private String createUserToken() {
        String userToken = Long.toString(new Date().getTime());
        return userToken;
    }

}
