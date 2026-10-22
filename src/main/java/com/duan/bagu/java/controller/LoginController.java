package com.duan.bagu.java.controller;

import com.duan.bagu.java.Service.UserService;
import com.duan.bagu.java.common.Result;
import com.duan.bagu.java.common.TokenUtils;
import com.duan.bagu.java.entity.User;
import com.duan.bagu.java.enums.ResultEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login/")
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "checkUser", method = RequestMethod.POST)
    public Result<String> checkUser(@RequestParam(required = false) String username,
                                    @RequestParam(required = false) String password) {

        // 参数校验
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return Result.<String>builder()
                    .code(ResultEnum.PARAM_INVALID.getCode())
                    .info(ResultEnum.PARAM_INVALID.getInfo())
                    .build();
        }

        // 用户名和密码校对
        User user = userService.getUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            return Result.<String>builder()
                    .code(ResultEnum.LOGIN_FAILED.getCode())
                    .info(ResultEnum.LOGIN_FAILED.getInfo())
                    .build();
        }

        // 状态：0删除 1正常 2拉黑
        if (user.getStatus() == null || user.getStatus() != 1) {
            return Result.<String>builder()
                    .code(ResultEnum.ACCOUNT_DISABLED.getCode())
                    .info(ResultEnum.ACCOUNT_DISABLED.getInfo())
                    .build();
        }

        // 校验通过，生成 token 存入 Redis，返回给前端
        String token = userService.createLoginToken(user);
        return Result.<String>builder()
                .code(ResultEnum.SUCCESS.getCode())
                .info(ResultEnum.SUCCESS.getInfo())
                .data(token)
                .build();
    }

    /**
     * 退出登录：删除 Redis 中的 token
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Result<String> logout(HttpServletRequest request) {
        String token = TokenUtils.getToken(request);
        if (token != null) {
            userService.removeLoginToken(token);
        }
        return Result.<String>builder()
                .code(ResultEnum.SUCCESS.getCode())
                .info(ResultEnum.SUCCESS.getInfo())
                .build();
    }

}
