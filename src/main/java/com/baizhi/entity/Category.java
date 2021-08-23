package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//生成get+set+tostring
@NoArgsConstructor//生产无参构造
@AllArgsConstructor//生成全参构造
public class Category {

  private String id;
  private String cate_name;
  private Integer levels;
  private String parent_id;


}
