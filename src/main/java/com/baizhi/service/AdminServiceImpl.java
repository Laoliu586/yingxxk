package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        //根据名字查询信息
        Admin admin = adminDao.queryByUserName(username);
        if(admin != null){
            //有账号
            if(admin.getPassword().equals(password)){
                    //登录
                map.put("flag",true);
                map.put("admin","登录成功");
            }else {
                //密码错误
                map.put("flag",false);
                map.put("msg","密码错误");
            }
        }else {
            //没有账号
            map.put("msg","用户名不存在");
        }
        return map;
    }

    }

