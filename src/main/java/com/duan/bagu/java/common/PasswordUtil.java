package com.duan.bagu.java.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密工具：BCrypt
 * <p>
 * BCrypt 每次加密都会加随机盐，同一个明文每次生成的密文都不同；
 * 校验时用 matches(明文, 密文)，不能直接比较字符串。
 */
public class PasswordUtil {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private PasswordUtil() {
    }

    /**
     * 加密明文密码
     */
    public static String encode(String rawPassword) {
        return ENCODER.encode(rawPassword);
    }

    /**
     * 判断密文是否为 BCrypt 格式
     */
    public static boolean isBcrypt(String password) {
        return password != null && (password.startsWith("$2a$")
                || password.startsWith("$2b$")
                || password.startsWith("$2y$"));
    }

    /**
     * 校验明文密码与密文是否匹配
     * <p>
     * 兼容历史明文数据：非 BCrypt 格式时按明文比较，登录成功后由调用方自动升级为 BCrypt。
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        if (isBcrypt(encodedPassword)) {
            return ENCODER.matches(rawPassword, encodedPassword);
        }
        return encodedPassword.equals(rawPassword);
    }
}
