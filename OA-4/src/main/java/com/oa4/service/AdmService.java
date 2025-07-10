package com.oa4.service;

import com.oa4.pojo.Admin;
import com.oa4.util.RESP;

import javax.servlet.http.HttpSession;


public interface AdmService {
    public String login(Admin admin, HttpSession session);
    public String register(Admin admin);

}
