package com.gm.api.param;

import lombok.Data;
import sun.dc.pr.PRError;

/**
 * Created by xxhh on 2018/5/27.
 */
@Data
public class LoginParam {
    private String userId;
    private String password;
}
