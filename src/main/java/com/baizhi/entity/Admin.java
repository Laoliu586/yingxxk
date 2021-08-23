package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*管理员*/
@Data//生成get+set+tostring
@NoArgsConstructor//生产无参构造
@AllArgsConstructor//生成全参构造
public class Admin {

  private String id;
  private String username;
  private String password;
  private long status;


}
