package com.atstudy.controller;

import com.atstudy.bean.bo.AddSpuBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SpuSearchBo;
import com.atstudy.bean.bo.UpdateSpuBo;
import com.atstudy.bean.po.Brand;
import com.atstudy.bean.po.Category;
import com.atstudy.bean.po.Spu;
import com.atstudy.bean.po.SpuAttrKey;
import com.atstudy.bean.vo.SpuVo;
import com.atstudy.service.IBrandService;
import com.atstudy.service.ICategoryService;
import com.atstudy.service.ISpuAttrKeyService;
import com.atstudy.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//视图类-负责商品业务
@Controller
@RequestMapping("/spu")
public class SpuController {
    //成员属性
    @Autowired
    ISpuService spuService;

    @Autowired
    IBrandService brandService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    ISpuAttrKeyService spuAttrKeyService;


    //视图方法
    @RequestMapping("/admin")
    public String admin(
            SpuSearchBo spuSearchBo,
            PageBo pageBo,
            Model model
    ) {
        //需要把查询条件，传给页面
        model.addAttribute("spuSearchBo",spuSearchBo);
        //把分页条件，传给页面
        model.addAttribute("pageBo",pageBo);

        //调用业务层的抽象方法，返回分页好的数据
        List<Spu> spuList = spuService.getListWithBrand(spuSearchBo,pageBo);
        model.addAttribute("spuList",spuList);

        //调用业务层方法，实现得到所有品牌数据的功能
        List<Brand> brandList = brandService.getAll();
        model.addAttribute("brandList",brandList);

        return null;
    }

    //修改商品-视图方法
    @RequestMapping("/update")
    public String update(
            Long spu_id,
            Model model
    ){
        //页面中需要spuvo的类型
        SpuVo spuVo = spuService.getVoBySpuId(spu_id);
        model.addAttribute("spu",spuVo);

        //需要所有品牌列表的数据
        List<Brand> brandList = brandService.getAll();
        model.addAttribute("brandList",brandList);

        //需要所有分类的数据
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList",categoryList);

        //需要所有筛选属性键的数据
        List<SpuAttrKey> filterList =spuAttrKeyService.getFilterAll();
        model.addAttribute("filterList",filterList);

        //需要所有规格属性键的数据
        List<SpuAttrKey> skuList = spuAttrKeyService.getSkuAll();
        model.addAttribute("skuList",skuList);

        return null;
    }

    //执行修改商品
    @RequestMapping("/alter")
    public String alter(
            UpdateSpuBo updateSpuBo
    ){
        //System.out.println(updateSpuBo);

        boolean result = false;        //初始变量是false

        //调用业务层的updateOne方法
        result = spuService.updateOne(updateSpuBo);

        if( result ){
            return "redirect:/index/success?message=Success!&detail=Spu Update Success!&controller=spu";
        }else{
            return "redirect:/index/error?message=Error!&detail=Spu Update Error!&controller=spu";
        }
    }

    //添加商品-视图方法
    @RequestMapping("/add")
    public String add(
            Model model
    ){
        //需要所有品牌列表的数据
        List<Brand> brandList = brandService.getAll();
        model.addAttribute("brandList",brandList);

        //需要所有分类的数据
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList",categoryList);

        //需要所有筛选属性键的数据
        List<SpuAttrKey> filterList =spuAttrKeyService.getFilterAll();
        model.addAttribute("filterList",filterList);

        //需要所有规格属性键的数据
        List<SpuAttrKey> skuList = spuAttrKeyService.getSkuAll();
        model.addAttribute("skuList",skuList);

        return null;
    }


    //执行修改商品
    @RequestMapping("/save")
    public String save(
            AddSpuBo addSpuBo
    ){
        //System.out.println(addSpuBo);

        boolean result = false;        //初始变量是false

        //调用业务层的addOne方法
        result = spuService.addOne(addSpuBo);

        if( result ){
            return "redirect:/index/success?message=Success!&detail=Spu Add Success!&controller=spu";
        }else{
            return "redirect:/index/error?message=Error!&detail=Spu Add Error!&controller=spu";
        }
    }


}
