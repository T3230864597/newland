package com.oa4.service;

import com.github.pagehelper.Page;
import com.oa4.pojo.Emp;
import com.oa4.util.RESP;
import javax.servlet.http.HttpSession;


/**
 * 员工管理业务逻辑接口
 * @author hp
 * @date 2025/06/12
 */
public interface EmpService {
    public RESP selectByPage(int currentPage,int pageSize);
    public void addEmp(Emp emp);
    public void updateEmp(Emp emp);
    public void delete(Emp emp);
    public RESP getDeptData();
    public RESP getDutyData();
    public void updateDD(Emp emp);
}
