package com.baizhi.conteoller;

import com.baizhi.entity.Category;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliYunOss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/video")
@CrossOrigin
public class VideoConteoller {
    private Logger log= LoggerFactory.getLogger(VideoConteoller.class);
    @Autowired
    private VideoService videoService;
    @RequestMapping("/queryByPage")
    public Map<String,Object> queryByPage(int page){
        int size =3;
        return videoService.queryByPage(page,size);
    }
    @RequestMapping("/add")
    public void add(MultipartFile video,String title,String brief,String id) {
        Video vid=   new Video(null,title,brief,null,null,null,new Category(id,null,null,null),null,null);
       /* String FileName = new Date().getTime()+video.getOriginalFilename();
        AliYunOss.uploadByBytes(video,"video/"+FileName);
      videos.setVideoPath("http://yinxa.oss-cn-beijing.aliyuncs.com/video/"+FileName);
      */
        videoService.add(video,vid);

    }
    @RequestMapping("/delete")
    public void delete(String id){
        AliYunOss.deleteFile(videoService.queryOne(id).getVideoPath());
        videoService.delete(id);
    }
}
