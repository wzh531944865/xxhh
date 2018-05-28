package com.gm.api.service;

import com.gm.api.util.AESUtils;
import com.gm.api.util.MD5Utils;
import org.springframework.stereotype.Component;

/**
 * Created by xxhh on 2018/5/29.
 */
@Component
public class PasswordService {
    public String deAESAndMd5(String aesPassword) {
        String result = "";
        try {
            String originPassword = AESUtils.decrypt(aesPassword);
            String md5Password = MD5Utils.MD5Encode(originPassword);
            result = md5Password;
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;

    }
}
