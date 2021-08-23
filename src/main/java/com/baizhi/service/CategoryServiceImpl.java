package com.baizhi.service;

import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDao categoryDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryByLevels(int levels) {
        return categoryDao.queryByLevels(levels);

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryByParentId(String id) {
        return  categoryDao.queryByParendId(id);

    }

    @Override
    public void save(Category category) {

        category.setId(UUID.randomUUID().toString());
        categoryDao.save(category);
    }

    @Override
    public void delete(String id) {

        categoryDao.delete(id);
    }

    @Override
    public Map<String, Object> delete1(String id) {
        Map<String, Object> map = new HashMap<>();
        List<Category> categories = categoryDao.queryId(id);
        if (CollectionUtils.isEmpty(categories)){
            categoryDao.delete(id);
            map.put("flag",true);
        }else {
            map.put("flag",false);
            map.put("msg","无法删除,有二级类别");
        }
        return map;
    }

    @Override
    public List<Category> queryId(String id) {
        return categoryDao.queryId(id);
    }
}
