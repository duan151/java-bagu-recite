package com.duan.bagu.java.common;

/**
 * 当前登录用户上下文（ThreadLocal）
 * <p>
 * 由 LoginInterceptor 在请求进入时写入，请求结束时必须清理，避免线程池复用导致数据串号。
 */
public class UserContext {

    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    private UserContext() {
    }

    public static void setUserId(Long userId) {
        USER_ID.set(userId);
    }

    public static Long getUserId() {
        return USER_ID.get();
    }

    public static void clear() {
        USER_ID.remove();
    }
}
