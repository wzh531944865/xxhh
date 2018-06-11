package com.gm.api.controller;

import com.gm.api.param.LoginParam;
import com.gm.api.param.RegisterParam;
import com.gm.api.resp.APIResponse;
import com.gm.api.service.PasswordService;
import com.gm.api.service.UserService;
import com.gm.api.util.AESUtils;
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
    @Autowired
    PasswordService passwordService;

    @ApiOperation(
            value = "用户注册",
            notes = "复杂对象，传在body中 加密方式  AES/CBC/PKCS5Padding + Base64 key和 iv见 服务器信息" +
                    "示例 123   加密结果为 ROlIm30HmTccUNM8N2dScA==",
            httpMethod = "POST",
            produces = "application/json"
    )
    @ApiImplicitParams({
    })
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public APIResponse<UserInfo> register(@RequestBody RegisterParam param) {
        APIResponse<UserInfo> apiResponse = new APIResponse<>();
        apiResponse.setCode(0);
        apiResponse.setMessage("成功");
        UserInfo userInfo = new UserInfo();
        try {
            String password = passwordService.deAESAndMd5(param.getPassword());
            String userId = userService.register(param.getNickName(), password, param.getEmail(), param.getMobileNo());
            userInfo.setUserId(userId);
        } catch (Exception e) {
            System.out.println(e);
            apiResponse.setCode(-1);
            apiResponse.setMessage("失败");
        }
        apiResponse.setData(userInfo);
        return apiResponse;
    }

    @ApiOperation(
            value = "用户登录",
            notes = "复杂对象，传在body中   加密方式  AES/CBC/PKCS5Padding + Base64  key  '_dabanyexiangkey'  iv '_dabanyexiangiv_'" +
                    " 示例 123   加密结果为 ROlIm30HmTccUNM8N2dScA==",
            httpMethod = "POST",
            produces = "application/json"
    )
    @ApiImplicitParams({
    })
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public APIResponse<UserInfo> login(@RequestBody LoginParam param) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(0);
        apiResponse.setMessage("成功");
        try {
            String password = passwordService.deAESAndMd5(param.getPassword());
            if (!userService.checkUserPassword(param.getUserId(), password)) {
                apiResponse.setCode(-1);
                apiResponse.setMessage("失败");
                return apiResponse;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        UserInfo userInfo = userService.login(param.getUserId());
        apiResponse.setData(userInfo);
        return apiResponse;
    }


    @ApiOperation(
            value = "用户注册 无需加密",
            notes = "复杂对象，传在body中",
            httpMethod = "POST",
            produces = "application/json"
    )
    @ApiImplicitParams({
    })
    @RequestMapping(value = "registerWithOutAES.do", method = RequestMethod.POST)
    public APIResponse<UserInfo> registerWithOutAES(@RequestBody RegisterParam param) {
        APIResponse<UserInfo> apiResponse = new APIResponse<>();
        apiResponse.setCode(0);
        apiResponse.setMessage("成功");
        UserInfo userInfo = new UserInfo();
        try {
            String userId = userService.register(param.getNickName(), param.getPassword(), param.getEmail(), param.getMobileNo());
            userInfo.setUserId(userId);
            apiResponse.setData(userInfo);
        }catch (Exception e){
            apiResponse.setCode(-1);
            apiResponse.setMessage("失败");
        }
        return apiResponse;
    }

    @ApiOperation(
            value = "用户登录  无需加密",
            notes = "复杂对象，传在body中",
            httpMethod = "POST",
            produces = "application/json"
    )
    @ApiImplicitParams({
    })
    @RequestMapping(value = "loginWithOutAES.do", method = RequestMethod.POST)
    public APIResponse<UserInfo> loginWWithOutAES(@RequestBody LoginParam param) {
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
