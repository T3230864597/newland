package com.oa4.service.Impl;

import com.oa4.service.AdmService;
import com.oa4.dao.AdmDao;
import com.oa4.pojo.Admin;
import com.liuvei.common.SysFun;
import com.oa4.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class AdmServiceImpl implements AdmService {

    @Autowired
    private AdmDao admDao;
    @Override
    public String login(Admin admin, HttpSession session)
    {
        Admin admin1=admDao.selectByName(admin);
        if(admin1!=null){
            if(admin1.getPwd().equals(SysFun.md5(admin.getPwd()))){
                session.setAttribute("admin",admin1);
                return "true";
            }
        }
        return "false";
    }

    @Override
    public String register(Admin admin)
    {
        //查找同名账户
        Admin admin1=admDao.selectByName(admin);

        if(admin1==null)
        {
            admin.setPwd(SysFun.md5(admin.getPwd()));
            int i=admDao.insertAdm(admin);
            if(i>0)
                return "true";
        }
            return "false";
    }

}
