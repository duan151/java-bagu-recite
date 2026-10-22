package com.duan.bagu.java.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户
 */
@Data
public class User {

    /** 主键 */
    private Long id;
    /** 登录账号 */
    private String username;
    /** 密码（BCrypt 加密后的密文） */
    private String password;
    /** 昵称 */
    private String nickname;
    /** 头像地址 */
    private String avatar;
    /** 角色：1普通用户 2管理员 */
    private Integer role;
    /** 状态：0删除 1正常 2拉黑 */
    private Integer status;
    /** 最后登录时间 */
    private LocalDateTime lastLoginTime;
    /** 创建时间 */
    private LocalDateTime createTime;
    /** 更新时间 */
    private LocalDateTime updateTime;
}
