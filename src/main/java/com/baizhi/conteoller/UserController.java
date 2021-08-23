package com.baizhi.conteoller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.AliYunOss;
import com.baizhi.util.FileUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private Logger log= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @RequestMapping("/queryByPage")
    public Map<String,Object> queryByPage(int page){
        int size =3;
    return userService.queryByPage(page,size);
    }
    @RequestMapping("/updateStatus")
    public void updateStatus(String id,int status){
        log.debug(id+"             "+status);
        userService.updateStatus(id, status);
    }
    @RequestMapping("/add")
    public void add(MultipartFile photo,User user) throws IOException {
        //
        String FileName = "yinxa/"+new Date().getTime()+photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
        user.setHeadimg(FileName);
        userService.add(user);
        FileUtil.UploadFile(photo,FileName);

    }
    @RequestMapping("/delete")
    public void delete(String id){
        FileUtil.delete(userService.queryOne(id).getHeadimg());
         userService.delete(id);
    }
    @RequestMapping("/queryAll")
    public void queryAll() throws IOException {
        List<User> users = userService.queryAll();
        for (User user:users){
            String headimg = user.getHeadimg();
            /*int i =headimg.lastIndexOf('/');
            String fileName = headimg.substring(i+1);*/
            AliYunOss.download(headimg);
            user.setHeadimg("d:\\photo\\"+headimg);
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息表", "用户表"), User.class, users);
        workbook.write(new FileOutputStream(new File("E:/yx_user.xls")));
        workbook.close();
    }

}
