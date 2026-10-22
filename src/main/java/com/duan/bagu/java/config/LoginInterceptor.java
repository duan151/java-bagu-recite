package com.duan.bagu.java.config;

import com.duan.bagu.java.common.Result;
import com.duan.bagu.java.common.TokenUtils;
import com.duan.bagu.java.common.UserContext;
import com.duan.bagu.java.enums.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录校验拦截器：校验 token 是否在 Redis 中有效
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String LOGIN_TOKEN_KEY_PREFIX = "login:token:";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 进入请求先清一次：防止线程池复用、上一次请求异常未清理导致的数据串用
        UserContext.clear();

        String token = TokenUtils.getToken(request);
        if (token != null) {
            String userId = stringRedisTemplate.opsForValue().get(LOGIN_TOKEN_KEY_PREFIX + token);
            if (userId != null) {
                // 把当前用户 id 放到 ThreadLocal 里，后续 Controller / Service 可直接取
                UserContext.setUserId(Long.valueOf(userId));
                return true;
            }
        }

        // 未登录：页面请求跳登录页，接口请求返回 JSON
        if (isPageRequest(request)) {
            response.sendRedirect("/login");
        } else {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(Result.fail(ResultEnum.NOT_LOGIN)));
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求结束清理 ThreadLocal，防止线程复用串数据
        UserContext.clear();
    }

    /**
     * 浏览器直接访问页面时 Accept 里带 text/html
     */
    private boolean isPageRequest(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        return accept != null && accept.contains("text/html");
    }
}
