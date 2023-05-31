package com.atstudy.controller;

import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SpuAttrKeySearchBo;
import com.atstudy.bean.po.SpuAttrKey;
import com.atstudy.service.ISpuAttrKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//视图类-负责属性键的业务
@Controller
@RequestMapping("/attr")
public class SpuAttrController {

    //成员属性
    @Autowired
    ISpuAttrKeyService spuAttrKeyService;

    //查询页面的视图方法
    @RequestMapping("/admin")
    public String admin(
            SpuAttrKeySearchBo spuAttrKeySearchBo,       //查询条件
            PageBo pageBo,                               //页面中得到分页信息
            Model model                                  //需要辅助变量，要传递数据给thymeleaf页面
    ){
        //调用业务层的getList的方法
        List<SpuAttrKey> spuAttrKeyList = spuAttrKeyService.getList(spuAttrKeySearchBo,pageBo);

        //把数据传给html页面
        model.addAttribute("spuAttrKeyList",spuAttrKeyList);
        model.addAttribute("spuAttrKeySearchBo",spuAttrKeySearchBo);
        model.addAttribute("pageBo",pageBo);

        return null;
    }
}
