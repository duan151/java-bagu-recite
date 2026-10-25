package com.duan.bagu.java.Service;

import com.duan.bagu.java.entity.User;

public interface UserService {

    User getUser(String username);

    /**
     * 按主键查询用户
     */
    User getUserById(Long id);

    /**
     * 生成登录 token：UUID 与用户绑定，存入 Redis，返回 token
     */
    String createLoginToken(User user);

    /**
     * 退出登录：删除 Redis 中的 token
     */
    void removeLoginToken(String token);

    /**
     * 更新密码（BCrypt 密文）
     */
    void updatePassword(Long id, String encodedPassword);

}
