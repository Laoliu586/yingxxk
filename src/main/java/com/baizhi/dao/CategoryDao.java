package com.baizhi.dao;

import com.baizhi.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryDao {
    //根据级别  查询 类别
    List<Category> queryByLevels(int levens);
    //根据 父项查询 二级id类别
    List<Category> queryByParendId(String id);
    //根据父像  添加二级类别
    void save(Category category);
    //根据id删除类别
    void delete(String id);
    Map<String,Object> delete1(String id);
    //根据父项id  查询
    List<Category> queryId(String id);
}
