package com.atstudy.controller;

import com.atstudy.bean.bo.AddCategoryBo;
import com.atstudy.bean.bo.CategorySearchBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.UpdateCategoryBo;
import com.atstudy.bean.po.Brand;
import com.atstudy.bean.po.Category;
import com.atstudy.bean.po.SpuAttrKey;
import com.atstudy.service.IBrandService;
import com.atstudy.service.ICategoryService;
import com.atstudy.service.ISpuAttrKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//分类视图类
@Controller
@RequestMapping("/category")
public class CategoryController {
    //成员属性
    @Autowired
    ICategoryService categoryService;

    @Autowired
    IBrandService brandService;

    @Autowired
    ISpuAttrKeyService spuAttrKeyService;


    //视图方法...按条件进行查询分类的数据
    // /category/admin
    @RequestMapping("/admin")
    public String admin(
            CategorySearchBo categorySearchBo,
            PageBo pageBo,
            Model model
    ){
        //System.out.println("接收页面参数,seach="+categorySearchBo);
        System.out.println("pagebo="+pageBo);

        List<Category> categoryAll = categoryService.getAll();      //用于select的
        List<Category> categoryList = categoryService.getListWithParent(categorySearchBo,pageBo);//用于表格绑定

        //把数据要绑定到model上，供thymeleaf调用
        model.addAttribute("categoryAll",categoryAll);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("categorySearchBo",categorySearchBo);//用于页面查询条件中的，信息绑定->收益：保留上一次的查询条件

        return null;
    }

    //删除分类的视图方法
    @RequestMapping("/delete")
    public String delete(
            Integer cate_id,//单选入参
            Integer[] id_list//批量入参
    )
    {
        System.out.println("单选删除入参:"+cate_id);
        System.out.println("多选删除入参："+id_list);
        boolean result = false;

        if(cate_id!=null && cate_id>0){
            //单选删除
            result = categoryService.deleteOne(cate_id);

        }else if(id_list!=null && id_list.length>0){
            //批量删除
            result = categoryService.multDel(id_list);

        }else{
            System.out.println("删除分类时，传入的参数是非法的");
        }

        if( result ){
            return "redirect:/index/success?message=Success!&detail=Category Delete Success!&controller=category";
        }else{
            return "redirect:/index/error?message=Error!&detail=Category Delete Error!&controller=category";
        }
    }

    //添加的视图方法
    @RequestMapping("/add")
    public String add(Model model){

        //绑定所有分类，到model
        List<Category> categoryAll = categoryService.getAll();
        model.addAttribute("categoryAll",categoryAll);

        //绑定所有品牌，到model
        List<Brand> brandAll = brandService.getAll();
        model.addAttribute("brandAll",brandAll);

        //绑定所有筛选属性列表，到model
        List<SpuAttrKey> spuAttrKeyAll = spuAttrKeyService.getFilterAll();
        model.addAttribute("spuAttrKeyAll",spuAttrKeyAll);

        return null;
    }

    //执行添加的视图方法
    @RequestMapping("/save")
    public String save(
            AddCategoryBo addCategoryBo
    ){
        //测试一下接收的参数：
//        System.out.println(addCategoryBo);
//        System.out.println(" brandList => "+addCategoryBo.getBrandList());
//        System.out.println(" spuAttrKeyList => "+addCategoryBo.getSpuAttrKeyList());


        boolean result = false;

        //调用业务层的方法，
        result = categoryService.addOne(addCategoryBo);

        if( result ){
            return "redirect:/index/success?message=Success!&detail=Category Add Success!&controller=category";
        }else{
            return "redirect:/index/error?message=Error!&detail=Category Add Error!&controller=category";
        }
    }

    //修改分类界面
    @RequestMapping("/update")
    public String update(
            Integer cate_id,            //从查询字符串中取值
            Model model){

        //绑定所有分类，到model
        List<Category> categoryAll = categoryService.getAll();
        model.addAttribute("categoryAll",categoryAll);

        //绑定所有品牌，到model
        List<Brand> brandAll = brandService.getAll();
        model.addAttribute("brandAll",brandAll);

        //绑定所有筛选属性列表，到model
        List<SpuAttrKey> spuAttrKeyAll = spuAttrKeyService.getFilterAll();
        model.addAttribute("spuAttrKeyAll",spuAttrKeyAll);

        //得到这个分类的所有数据
        Category category = categoryService.getOneWithBrandAndAttr(cate_id);
        model.addAttribute("category",category);


        return null;
    }

    //执行修改分类
    @RequestMapping("/alter")
    public String alter(
            UpdateCategoryBo updateCategoryBo
    ){
        boolean result = false;

        //调用业务层的方法，
        result = categoryService.updateOne(updateCategoryBo);

        if( result ){
            return "redirect:/index/success?message=Success!&detail=Category Update Success!&controller=category";
        }else{
            return "redirect:/index/error?message=Error!&detail=Category Update Error!&controller=category";
        }

    }
}
