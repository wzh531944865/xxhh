package com.gm.api.controller;

import com.gm.api.param.LoginParam;
import com.gm.api.param.RegisterParam;
import com.gm.api.resp.APIResponse;
import com.gm.api.service.UserService;
import com.gm.api.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xxhh on 2018/5/27.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public APIResponse<UserInfo> register(@RequestBody RegisterParam param) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(0);
        apiResponse.setMessage("成功");
        userService.register(param.getNickName(), param.getPassword(), param.getEmail(), param.getMobileNo());
        return apiResponse;
    }

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public APIResponse<UserInfo> register(@RequestBody LoginParam param) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(0);
        apiResponse.setMessage("成功");

        if (!userService.checkUserPassword(param.getUserId(), param.getPassword())) {
            apiResponse.setCode(-1);
            apiResponse.setMessage("失败");
            return apiResponse;
        }
        UserInfo userInfo = userService.login(param.getUserId());
        apiResponse.setData(userInfo);
        return apiResponse;
    }
}
