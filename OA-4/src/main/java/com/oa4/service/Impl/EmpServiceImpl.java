package com.oa4.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa4.pojo.Department;
import com.oa4.pojo.Duty;
import com.oa4.service.EmpService;
import com.oa4.dao.EmpDao;
import com.oa4.pojo.Emp;
import com.oa4.util.JediPoolUtil;
import com.oa4.util.PageData;
import com.oa4.util.RESP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;


@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpDao empDao;

    @Autowired
    JediPoolUtil jediPoolUtil;
    @Override
    public RESP selectByPage(int currentPage,int pageSize)
    {
        String redisKey = "emp:page:" + currentPage + ":" + pageSize;
// 从 Redis 缓存中获取
        try (Jedis jedis = jediPoolUtil.getJedis()) {
            String json = jedis.get(redisKey);
            if (json != null) {
                // 命中缓存，反序列化返回
                PageData pageData = JSON.parseObject(json, PageData.class);
                System.out.println("Redis存储的员工为: "+ jedis.get(redisKey));
                return RESP.ok(pageData.getList(), pageData.getCurrentPage(), pageData.getTotal());
            }
            // 缓存未命中，查数据库
            System.out.println("2");
            PageHelper.startPage(currentPage, pageSize);
            List<Emp> list = empDao.selectByPageHelper();
            PageInfo<Emp> pageInfo = new PageInfo<>(list);

            // 包装分页数据
            PageData pageData = new PageData(pageInfo.getList(), pageInfo.getPageNum(), (int) pageInfo.getTotal());

            // 缓存到 Redis（设置过期时间）
            jedis.setex(redisKey, 300, JSON.toJSONString(pageData)); // 缓存 5 分钟

            return RESP.ok(pageData.getList(), pageData.getCurrentPage(), pageData.getTotal());


        }
        /*PageHelper.startPage(currentPage,pageSize);
        List<Emp> list =empDao.selectByPageHelper();
        PageInfo<Emp> date=new PageInfo<>(list);
        return RESP.ok(date.getList(),date.getPageNum(),(int)date.getTotal());*/
    }
    @Override
    public void addEmp(Emp emp)
    {

        empDao.addEmp(emp);
        //更新后删除缓存
        deleteRedis();

    }
    @Override
    public void updateEmp(Emp emp)
    {
        empDao.updateEmp(emp);
        deleteRedis();
    }
    @Override
    public void delete(Emp emp)
    {
        //后期要删该员工对应的打卡记录,打卡记录引用了该员工的主键，所以现在不能直接删除
        empDao.deleteEmp(emp);
        empDao.deleteEmpSignByNumber(emp);
        deleteRedis();
    }

    @Override
    public RESP getDeptData()
    {
        String redisKey = "dept:list";

        try (Jedis jedis = jediPoolUtil.getJedis()) {
            String json = jedis.get(redisKey);
            if (json != null) {
                // 缓存命中：从 Redis 取出
                List<Department> list = JSON.parseArray(json, Department.class);
                System.out.println("Redis存储的部门为: "+ jedis.get(redisKey));
                return RESP.ok(list);
            }

            // 缓存未命中：查数据库
            List<Department> list = empDao.getDeptData();

            // 存入 Redis，5 分钟过期
            jedis.setex(redisKey, 300, JSON.toJSONString(list));

            return RESP.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            // 出现异常也保证正常查库返回
            return RESP.ok(empDao.getDeptData());
        }
        //return RESP.ok(empDao.getDeptData());
    }

    @Override
    public RESP getDutyData()
    {
        String redisKey = "duty:list";

        try (Jedis jedis = jediPoolUtil.getJedis()) {
            String json = jedis.get(redisKey);
            if (json != null) {
                // Redis 缓存命中
                List<Duty> list = JSON.parseArray(json, Duty.class);
                System.out.println("Redis存储的职务为: "+ jedis.get(redisKey));
                return RESP.ok(list);
            }

            // 缓存未命中，查数据库
            List<Duty> list = empDao.getDutyData();

            // 写入 Redis，设置过期时间（300 秒 = 5 分钟）
            jedis.setex(redisKey, 300, JSON.toJSONString(list));

            return RESP.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            // Redis 出问题，也保证查数据库返回
            return RESP.ok(empDao.getDutyData());
        }
        //return RESP.ok(empDao.getDutyData());
    }

    @Override
    public void updateDD(Emp emp)
    {

        empDao.updateDD(emp);
        deleteRedis();
    }

    public void deleteRedis()
    {
        Jedis jedis=jediPoolUtil.getJedis();

        Set<String> keys = jedis.keys("emp:page:*");
        for (String key : keys) {
            jedis.del(key);
        }

    }
}
