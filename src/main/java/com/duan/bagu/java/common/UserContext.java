package com.duan.bagu.java.common;

import com.duan.bagu.java.entity.User;

/**
 * 当前登录用户上下文（ThreadLocal）
 * <p>
 * 由 LoginInterceptor 在请求进入时写入完整用户信息，请求结束时必须清理，避免线程池复用导致数据串号。
 */
public class UserContext {

    private static final ThreadLocal<User> USER = new ThreadLocal<>();

    private UserContext() {
    }

    /**
     * 放入当前登录用户（完整信息）
     */
    public static void setUser(User user) {
        USER.set(user);
    }

    /**
     * 获取当前登录用户（完整信息）
     */
    public static User getUser() {
        return USER.get();
    }

    /**
     * 获取当前登录用户 id
     */
    public static Long getUserId() {
        User user = USER.get();
        return user == null ? null : user.getId();
    }

    public static void clear() {
        USER.remove();
    }
}
