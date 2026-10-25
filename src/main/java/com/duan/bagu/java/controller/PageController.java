package com.duan.bagu.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面路由
 */
@Controller
public class PageController {

    /**
     * 首页
     */
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * 登录页
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 题库页
     */
    @GetMapping("/bagu")
    public String bagu() {
        return "bagu";
    }

    /**
     * 个人中心
     */
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}
