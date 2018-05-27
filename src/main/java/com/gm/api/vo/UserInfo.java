package com.gm.api.vo;

import lombok.Data;

/**
 * Created by xxhh on 2018/5/27.
 */
@Data
public class UserInfo {
    private String userId;
    private String nickName;
    private String name;
    private String gender;
    private String age;
    private String mobileNo;
    private String email;
    private String idCode;

    private String userToken;
}
