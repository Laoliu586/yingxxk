package com.baizhi.dao;

import com.baizhi.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    //分页查询
    List<Video> queryByPage(@Param("start") int start, @Param("end") int end);
    //查条数
    int queryPageNum();
    //添加用户
    void add(Video video);
  //删除用户
    void delete(String id);
    //查一个
    Video queryOne(String id);
}
