package com.atstudy.controller;

import com.atstudy.bean.bo.AddSkuBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SkuSearchBo;
import com.atstudy.bean.po.Sku;
import com.atstudy.bean.po.Spu;
import com.atstudy.service.ISkuService;
import com.atstudy.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//视图类-负责sku规格
@Controller
@RequestMapping("/sku")
public class SkuController {

    //上层视图类，调用下层ISkuService
    @Autowired
    ISkuService skuService;

    @Autowired
    ISpuService spuService;

    //sku管理的视图方法
    @RequestMapping("/admin")
    public String admin(
            SkuSearchBo skuSearchBo,
            PageBo pageBo,
            Model model
    ){
        //调用业务层，返回List<Sku>
        List<Sku> skuList = skuService.getList(skuSearchBo,pageBo);

        //把List<Sku>放到model中，用于页面的绑定
        model.addAttribute("skuList",skuList);

        //把skuSearchBo放到model中，用于页面的绑定
        model.addAttribute("skuSearchBo",skuSearchBo);

        //把当前的分页信息，传递给thymeleaf页面，用于数据绑定
        model.addAttribute("pageBo",pageBo);

        return  null;
    }

    //添加sku的界面
    @RequestMapping("/add")
    public String add(
            Model model
    ){
        //从业务层，得到所有商品列表，放到model中
        List<Spu> spuList = spuService.getAll();
        model.addAttribute("spuList",spuList);

        return null;
    }

    //执行添加sku
    @RequestMapping("/save")
    public String save(
            AddSkuBo addSkuBo
    ){
        System.out.println(addSkuBo);

        boolean result = false;        //初始变量是false

        //调用业务层的addOne方法
        result = skuService.addOne(addSkuBo);

        if( result ){
            return "redirect:/index/success?message=Success!&detail=Sku Add Success!&controller=sku";
        }else{
            return "redirect:/index/error?message=Error!&detail=Sku Add Error!&controller=sku";
        }
    }
}
