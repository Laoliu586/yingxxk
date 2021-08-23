package com.baizhi.service;



import com.baizhi.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    //根据级别查询
    List<Category> queryByLevels(int levens);

    //根据二级类别查询
    List<Category> queryByParentId(String id);

    //根据父像  添加二级类别
    void save(Category category);
    //删除id
    void delete(String id);
    Map<String,Object> delete1(String id);
    //根据父类id查询二级
    List<Category> queryId(String id);
}
