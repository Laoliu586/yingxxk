package com.baizhi.service;

import com.baizhi.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface VideoService {

    Map<String,Object> queryByPage(int page, int size);
    //查条数
    int queryPageNum();
    //添加用户
    void add(MultipartFile file,Video video);
    //删除用户
    void delete(String id);
    //查一个
    Video queryOne(String id);
}
