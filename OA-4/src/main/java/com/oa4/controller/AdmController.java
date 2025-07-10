package com.oa4.controller;

import com.oa4.service.AdmService;
import com.oa4.pojo.Admin;
import com.oa4.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

/**
 * 管理员登录与注册控制器
 */

@Controller
public class AdmController {

    @Autowired
    AdmService admService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(Admin admin,HttpSession session)
    {

        return admService.login(admin,session);
    }
    @RequestMapping("/register")
    @ResponseBody
    public String register(Admin admin)
    {
        return admService.register(admin);
    }


}
