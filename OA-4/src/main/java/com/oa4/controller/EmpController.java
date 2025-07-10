package com.oa4.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.oa4.pojo.Department;
import com.oa4.pojo.Emp;
import com.oa4.service.EmpService;
import com.oa4.dao.EmpDao;
import com.oa4.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 员工管理控制器
 */

@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping("/selectByPage")
    @ResponseBody
    public RESP selectByPage(@RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize")String pageSize)
    {
        return empService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
    }

    @RequestMapping("/addEmp")
    @ResponseBody
    public RESP addEmp(Emp emp,@RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize")String pageSize)
    {
        empService.addEmp(emp);
        return empService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

    }

    @RequestMapping("/updateEmp")
    @ResponseBody
    public RESP updateEmp(Emp emp,@RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize")String pageSize)
    {
        empService.updateEmp(emp);
        return empService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

    }

    @RequestMapping("/delete")
    @ResponseBody
    public RESP delete(Emp emp,@RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize")String pageSize)
    {
        empService.delete(emp);

        return empService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

    }

    @RequestMapping("/getDeptData")
    @ResponseBody
    public RESP getDeptData()
    {
        return empService.getDeptData();
    }

    @RequestMapping("/getDutyData")
    @ResponseBody
    public RESP getDutyData()
    {
        return empService.getDutyData();
    }

    @RequestMapping("/updateDD")
    @ResponseBody
    public RESP updateDD(Emp emp,@RequestParam(name = "currentPage") String currentPage, @RequestParam(name = "pageSize")String pageSize)
    {
        empService.updateDD(emp);
        return empService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

    }
}
