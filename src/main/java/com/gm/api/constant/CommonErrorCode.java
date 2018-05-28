package com.gm.api.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by xxhh on 2018/3/12.
 */
@Getter
@AllArgsConstructor
public enum CommonErrorCode {
    OK(0, "成功"),
    ERROR(-1, "失败"),

    GROUP_CONFLICT(100, "分组冲突"),
    NO_AUTH(101, "没有权限"),
    EXP_NOT_EXIST(102, "实验不存在"),
    EXP_HAVE_EXIST(103, "实验已存在"),

    WRONG_INPUT(200, "输入不符合规范"),
    WRONG_EMAIL_RULE(201, "错误的邮箱格式"),
    WRONG_JSON_RULE(202, "错误的JSON格式"),
    INPUT_TOO_LONG(202, "输入过长"),

    FACTOR_EXIST_EXP(1000, "当前factor存在生效实验"),

    FALL_BACK(9998, "调用微服务失败，默认fallback"),
    UNKNOWN(9999, "未知错误");

    private int code;
    private String msg;

}
