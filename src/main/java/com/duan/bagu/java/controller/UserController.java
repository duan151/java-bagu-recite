package com.duan.bagu.java.controller;

import com.duan.bagu.java.Service.UserService;
import com.duan.bagu.java.common.Result;
import com.duan.bagu.java.common.UserContext;
import com.duan.bagu.java.entity.User;
import com.duan.bagu.java.enums.ResultEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户信息
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 当前登录用户信息（不含密码）
     * <p>
     * 说明：拦截器已把完整用户信息放入 UserContext，这里暂时保留「按 userId 查库」的写法不变，
     * 方便对照两种取值方式；后续如需可改为直接使用 UserContext.getUser()。
     */
    @GetMapping("info")
    public Result<User> info() {
        Long userId = UserContext.getUserId();
        if (userId == null) {
            return Result.fail(ResultEnum.NOT_LOGIN);
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            return Result.fail(ResultEnum.DATA_NOT_FOUND);
        }
        user.setPassword(null);
        return Result.success(user);
    }
}
