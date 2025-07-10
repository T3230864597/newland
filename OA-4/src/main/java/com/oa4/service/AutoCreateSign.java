package com.oa4.service;

import com.oa4.dao.EmpDao;
import com.oa4.dao.SignDao;
import com.oa4.pojo.Sign;
import com.oa4.util.DU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * 定时器任务控制器
 */

@Configuration
@EnableScheduling
public class AutoCreateSign {


}
