package com.oa4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;


@Controller
public class IndexController {

//    管理员登录页面
    @RequestMapping("/")
    public String admin(){
        return "login";
    }

//    管理员首页
    @RequestMapping("/main")
    public String main(){
        return "index";
    }

    //用户退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/";
    }
}
