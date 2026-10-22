package com.duan.bagu.java.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一返回状态
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    /** 成功 */
    SUCCESS("0000", "成功"),
    /** 失败 */
    FAIL("0001", "失败"),
    /** 参数非法 */
    PARAM_INVALID("0002", "参数非法"),
    /** 账号或密码不正确 */
    LOGIN_FAILED("0003", "账号或密码不正确"),
    /** 账号已被禁用 */
    ACCOUNT_DISABLED("0004", "账号已被禁用"),
    /** 未登录或登录已过期 */
    NOT_LOGIN("0005", "未登录或登录已过期"),
    /** 数据不存在 */
    DATA_NOT_FOUND("0006", "数据不存在");

    /** 状态码 */
    private final String code;

    /** 提示信息 */
    private final String info;
}
