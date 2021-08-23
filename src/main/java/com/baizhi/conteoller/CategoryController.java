package com.baizhi.conteoller;

import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/queryByLevels")
    public List<Category>queryByLevels(int levels){
       return   categoryService.queryByLevels(levels);
    }
    @RequestMapping("/queryByParentId")
    public List<Category>queryByParentId(String id){
        return   categoryService.queryByParentId(id);
    }
    @RequestMapping("/save")
    public void save(@RequestBody Category category ){
      categoryService.save(category);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        categoryService.delete(id);
    }
    @RequestMapping("/delete2")
    Map<String,Object> delete2(String id){
     return categoryService.delete1(id);

    }
}
