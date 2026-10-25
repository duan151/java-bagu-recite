package com.duan.bagu.java.mapper;

import com.duan.bagu.java.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 用户 Mapper
 */
@Mapper
public interface UserMapper {

    /**
     * 按主键查询
     */
    User selectById(@Param("id") Long id);

    /**
     * 按登录账号查询（登录、注册查重用）
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 新增用户，回填主键
     */
    int insert(User user);

    /**
     * 按主键更新，只更新非空字段（昵称、头像、密码、角色、状态等）
     */
    int updateById(User user);

    /**
     * 更新最后登录时间
     */
    int updateLastLoginTime(@Param("id") Long id, @Param("lastLoginTime") LocalDateTime lastLoginTime);

    /**
     * 更新状态：0删除 1正常 2拉黑
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 更新密码（BCrypt 密文）
     */
    int updatePassword(@Param("id") Long id, @Param("password") String password);
}
