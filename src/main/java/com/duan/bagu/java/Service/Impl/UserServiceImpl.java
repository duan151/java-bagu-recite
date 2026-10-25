package com.duan.bagu.java.Service.Impl;

import com.duan.bagu.java.Service.UserService;
import com.duan.bagu.java.entity.User;
import com.duan.bagu.java.mapper.UserMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    /** 登录 token 在 Redis 中的 key 前缀 */
    private static final String LOGIN_TOKEN_KEY_PREFIX = "login:token:";
    /** token 有效期（天） */
    private static final long LOGIN_TOKEN_TTL_DAYS = 7;

    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public User getUser(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public String createLoginToken(User user) {
        // UUID 去掉中划线作为 token
        String token = UUID.randomUUID().toString().replace("-", "");
        // Redis：token -> userId，7 天过期
        stringRedisTemplate.opsForValue().set(LOGIN_TOKEN_KEY_PREFIX + token,
                String.valueOf(user.getId()), LOGIN_TOKEN_TTL_DAYS, TimeUnit.DAYS);
        // 记录最后登录时间
        userMapper.updateLastLoginTime(user.getId(), LocalDateTime.now());
        return token;
    }

    @Override
    public void removeLoginToken(String token) {
        stringRedisTemplate.delete(LOGIN_TOKEN_KEY_PREFIX + token);
    }

    @Override
    public void updatePassword(Long id, String encodedPassword) {
        userMapper.updatePassword(id, encodedPassword);
    }
}
