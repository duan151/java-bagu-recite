package com.duan.bagu.java.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * token 读取工具：优先 Cookie，其次 Authorization: Bearer xxx
 */
public class TokenUtils {

    private TokenUtils() {
    }

    public static String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }
}
