package com.atstudy.controller;

import com.atstudy.bean.bo.AddBrandBo;
import com.atstudy.bean.bo.SearchBrandBo;
import com.atstudy.bean.po.Brand;
import com.atstudy.config.FastClientConfig;
import com.atstudy.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//视图类-负责品牌的访问
@Controller
@RequestMapping("/brand")
public class BrandController {

    //成员属性
    @Autowired              //需要由spring框架，实例化这个成员属性
    IBrandService brandService;

    @Autowired
    FastClientConfig fastClientConfig;


    //查询品牌列表的视图方法
    @RequestMapping("/admin")        //访问到/brand/admin的时候，定位到当前视图文件
    public String admin(
            SearchBrandBo searchBrandBo ,    //用参数对象化填充的形式去做，方便一些
            Model model                        //需要返回数据给list.html页面
    ){
        List<Brand> brandList
                = brandService.getList(searchBrandBo);

        model.addAttribute("brandList",brandList);      //添加列表数据到model，供list.html页面使用
        model.addAttribute("imgUrl","http://192.168.137.91/");  //存放fdsf的图片路径

        return null;
    }

    //添加品牌的视图方法
    @RequestMapping("/add")
    public String add(){
        return null;                //采用/brand/add.html视图响应
    }

    //添加品牌执行的视图方法
    @RequestMapping("/save")
    public String save(AddBrandBo addBrandBo){
        int status = -1;        //如果status返回大于0，说明受影响行数超过0行-》添加成功

        //调用service层的方法；
        status = brandService.add(addBrandBo);

        // 成功跳转到
        if(status>0){
            //返回统一success的页面
            return "redirect:/index/success?message=Success!&detail=Brand Add Success!&controller=brand";
        }
        else {
            //返回统一error的页面
            return "redirect:/index/error?message=Error!&detail=Brand Add Error!&controller=brand";
        }
    }
}
