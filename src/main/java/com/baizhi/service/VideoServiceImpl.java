package com.baizhi.service;

import com.baizhi.dao.VideoDao;
import com.baizhi.entity.Video;
import com.baizhi.util.AliYunOss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Transactional
public class VideoServiceImpl implements VideoService{
    @Autowired
    private VideoDao videoDao;

    @Override
    public Map<String, Object> queryByPage(int page, int size) {
        Map<String,Object> map = new HashMap<>();
        List<Video> videos = videoDao.queryByPage((page -1) * size,size);
        //当前的数据
        map.put("data",videos);
        //当前页数
        map.put("page",page);
        //总页数   总条数/每页显示的条数=总页数
        int count = videoDao.queryPageNum();//总条数
        int pageNum =count%size == 0 ? count/size:count/size+1;
        map.put("pageNum",pageNum);
        return map;
    }

    @Override
    public int queryPageNum() {
        return 0;
    }

    @Override
    public void add(MultipartFile file, Video video) {
        long time =new Date().getTime();
        String fileName = time+file.getOriginalFilename();
        AliYunOss.uploadByBytes(file,"video/"+fileName);
        //将视频第一帧  保存有时间限制
        String s = AliYunOss.jqVideo("video/" + fileName);
        System.out.println(s);
        //将视频的第一帧  保存到阿里云
        int i = fileName.lastIndexOf('.');
        String substring = fileName.substring(0, i);
        AliYunOss.URLuploal(s,substring+".jpg");
        //数据入库
        video.setId(UUID.randomUUID().toString());
        video.setCoverPath("http://yinxa.oss-cn-beijing.aliyuncs.com/video/"+substring+".jpg");//封面路径
        video.setCreateDate(new Date());
        videoDao.add(video);
    }


    @Override
    public void delete(String id) {
        videoDao.delete(id);
    }

    @Override
    public Video queryOne(String id) {
        return videoDao.queryOne(id);
    }
}
