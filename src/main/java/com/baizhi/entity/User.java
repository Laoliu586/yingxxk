package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data//生成get+set+tostring
@NoArgsConstructor//生产无参构造
@AllArgsConstructor//生成全参构造
public class User {
  @Excel(name = "用户id")
  private String id;
  @Excel(name = "姓名")
  private String username;
  @Excel(name="电话")
  private String phone;//电话
  @Excel(name = "头像")
  private String headimg;//头像
  @Excel(name = "描述")
  private String brief;//描述
  private String wechat;//微信
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date createdate;//注册时间
  private Integer status;//状态



}
