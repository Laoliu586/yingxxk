package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userdao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(int page, int size) {
        Map<String,Object> map = new HashMap<>();
        List<User> users = userdao.queryRange((page - 1) * size, size);
        //当前的数据
        map.put("data",users);
        //当前页数
        map.put("page",page);
        //总页数   总条数/每页显示的条数=总页数
        int count = userdao.queryPageNum();//总条数
        int pageNum =count%size == 0 ? count/size:count/size+1;
        map.put("pageNum",pageNum);
        return map;
    }

    @Override
    public void updateStatus(String id, int status) {
        userdao.updateStatus(id, status);
    }

    @Override
    public void add(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setCreatedate(new Date());
        userdao.add(user);
    }

    @Override
    public void delete(String id) {
        userdao.delete(id);
    }

    @Override
    public User queryOne(String id) {

        return userdao.queryOne(id);
    }

    @Override
    public List<User> queryAll() {
        return userdao.queryAll();
    }


}
