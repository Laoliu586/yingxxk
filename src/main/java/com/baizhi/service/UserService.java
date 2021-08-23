package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> queryByPage(int page,int size);
    //修改状态
    void updateStatus(String id,int status);
    //添加用户
    void add(User user);
    //删除用户
    void delete(String id);
    //查一个
    User queryOne(String id);
    //查所有
    List<User> queryAll();
}
