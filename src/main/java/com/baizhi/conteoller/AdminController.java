package com.baizhi.conteoller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin//解决跨域
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    private Logger log= LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("login")
   public Map<String,Object> login(@RequestBody Admin admin){
      log.debug(admin.toString());
       return adminService.login(admin.getUsername(),admin.getPassword());
    }

    }

