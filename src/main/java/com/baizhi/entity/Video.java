package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

@NoArgsConstructor//生产无参构造
@AllArgsConstructor//生成全参构造
public class Video {

  private String id;
  private String title;
  private String brief;
  private String coverPath;
  private String videoPath;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date createDate;
  private Category category;
  private User user;
  private String groupId;

}
