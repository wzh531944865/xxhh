package com.gm.api.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xxhh on 2018/5/27.
 */
@Data
public class APIResponse<T> implements Serializable {
    private static final long serialVersionUID = 8504037341132973717L;
    public static final APIResponse<?> OK = new APIResponse();
    private int code;
    private String message;
    private T data;

    public APIResponse() {
        this(0);
    }

    public APIResponse(int code) {
        this.code = code;
    }
}
