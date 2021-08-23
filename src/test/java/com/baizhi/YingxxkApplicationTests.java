package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.CategoryDao;
import com.baizhi.dao.UserDao;
import com.baizhi.dao.VideoDao;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.service.UserService;
import com.baizhi.service.VideoService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest
class YingxxkApplicationTests {
	@Autowired
	private UserDao userdao;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private VideoDao videoDao;
	@Autowired
	private VideoService videoService;
	@Test
	void contextLoads() {
/*		Admin admin1 = adminService.login("123");
		System.out.println(admin1);*/
		//String id, @Param("username") String usrename, @Param("phone") String phone, @Param("headimg") String headimg, @Param("brief") String brief, String wechat, Date createdate,Integer status);
		//userdao.add("5", "5", "5", "https://img1.baidu.com/it/u=2031644624,3034184646&fm=26&fmt=auto&gp=0.jpg", "5",null,new Date(),0);
		//userdao.add(new User("111", "5", "5", "https://img1.baidu.com/it/u=2031644624,3034184646&fm=26&fmt=auto&gp=0.jpg", "5",null,new Date(),0));
		//}
	/*	//userService.add("6","6","6","http://p0.ifengimg.com/pmop/2017/1112/5B5AC97D4812DFC647D3DABD156DE8BEA0CF2824_size40_w640_h532.jpeg","6",null);
		List<Category> categories = categoryDao.queryId("9");
		System.out.println(categories);
		if (CollectionUtils.isEmpty(categories)){
			System.out.println("为空");
		}else {
			System.out.println("2");
		}*/
		/*List<User> users = userdao.queryAll();
		System.out.println(users);*/
		//List<User> users = userdao.queryAll();
	}

	@Test
	public void addService(){
		//userService.add(new User("2222", "5", "5", "https://img1.baidu.com/it/u=2031644624,3034184646&fm=26&fmt=auto&gp=0.jpg", "5",null,new Date(),0));
	}
	@Test
	public void context(){
		/*List<Video> videos = videoDao.queryByPage(0,1);
		for (Video video:videos){
			System.out.println(video);
		}*/
		/*int pageNum = videoDao.queryPageNum()
		System.out.println(pageNum);*/
		/*Map<String, Object> map = videoService.queryByPage(1, 2);
		System.out.println(map);*/
		videoDao.add( new Video("5","hha","hahh",null,"hah",new Date(),null,null,null));
	}
	@Test
	public void queryAll() throws IOException {
		List<User> users = userService.queryAll();
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息表", "用户表"), User.class, users);
		workbook.write(new FileOutputStream(new File("E:/yx_user.xls")));
		workbook.close();
	}
}
