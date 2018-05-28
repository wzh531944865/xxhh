package com.gm.api.controller;

import com.gm.api.param.LoginParam;
import com.gm.api.param.RegisterParam;
import com.gm.api.resp.APIResponse;
import com.gm.api.service.UserService;
import com.gm.api.vo.UserInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(
        value = "用户注册",
        notes = "复杂对象，传在body中",
        httpMethod = "POST",
        produces = "application/json"
    )
    @ApiImplicitParams({
//        @ApiImplicitParam(name = "expId", value = "实验ID", required = true, dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public APIResponse<UserInfo> register(@RequestBody RegisterParam param) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(0);
        apiResponse.setMessage("成功");
        userService.register(param.getNickName(), param.getPassword(), param.getEmail(), param.getMobileNo());
        return apiResponse;
    }

    @ApiOperation(
        value = "用户登录",
        notes = "复杂对象，传在body中",
        httpMethod = "POST",
        produces = "application/json"
    )
    @ApiImplicitParams({
//        @ApiImplicitParam(name = "expId", value = "实验ID", required = true, dataType = "String", paramType = "query"),
    })
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
