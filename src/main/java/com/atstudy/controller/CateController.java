package com.atstudy.controller;


import com.atstudy.bean.po.Category;
import com.atstudy.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@Controller                 //需要托管给spring
@RequestMapping("/cate")    //所有的/cate/**访问都要定位到当前视图方法
public class CateController {

    //
    @Autowired
    ICategoryService categoryService;

    //通过分类的id，得到一个分类的信息
    // /cate/1..............得到1号分类的信息
    // /cate/2..............得到2号分类的信息
    @RequestMapping("/{cate_id}")
    //@ResponseBody                       //直接响应策略：不需要有视图页面，直接在网页中显示分类的id/分类的名称
    public String getOne(
            @PathVariable("cate_id")
            Integer cate_id){
        Category category;

        //访问category业务层，得到1个分类的实体信息
        category = categoryService.getOne(cate_id);

        if(category!=null){
            return category.toString();
        }
        else {
            return "数据为空,cate_id="+cate_id;
        }
    }
}
