package com.gm.api.param;

import lombok.Data;

/**
 * Created by xxhh on 2018/5/27.
 */
@Data
public class RegisterParam {
    private String nickName;
    private String password;
    private String email;
    private String mobileNo;
}
