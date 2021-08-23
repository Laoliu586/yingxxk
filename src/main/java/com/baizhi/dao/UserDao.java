package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //范围查询
    List<User> queryRange(@Param("start") int start, @Param("end") int end);
    //查条数
    int queryPageNum();
    //修改状态
    void updateStatus(@Param("id") String id,@Param("status") int status);
    //添加用户
    void add(User user);
    //删除用户
    void delete(String id);
    //查一个
    User queryOne(String id);
    //查所有
    List<User> queryAll();

}
