package com.atstudy.controller;

import com.alibaba.fastjson.JSON;
import com.atstudy.bean.po.SpuAttrValue;
import com.atstudy.bean.vo.AttributeVo;
import com.atstudy.service.ISpuAttrKeyService;
import com.atstudy.service.ISpuAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//视图类-负责属性值的业务
@Controller                 //需要托管给spring框架
@RequestMapping("/attr-value")
public class SpuAttrValueController {
    @Autowired
    ISpuAttrValueService spuAttrValueService;

    @Autowired
    ISpuAttrKeyService spuAttrKeyService;

    //通过一个key_id来返回，属性值列表的信息
    @RequestMapping("/getListByKeyId")
    @ResponseBody                       //用直接响应的策略
    public String getListByKeyId(String key_id){

        //，调用业务方法，返回List<SpuAttrValue>
        List<SpuAttrValue> spuAttrValueList = spuAttrValueService.getListByKeyId(key_id);

        //用fastjson中的JSON类型，要做转换成json字符串，再直接输出

        return JSON.toJSONString(spuAttrValueList);
    }

    //通过商品编号，得到一个规格属性值列表的数据
    @RequestMapping("/getSkuAttrKeyListBySpuId")
    @ResponseBody
    public String getSkuAttrKeyListBySpuId(Long spu_id){
        List<AttributeVo> list = spuAttrKeyService.getSkusBySpuId(spu_id);

        return JSON.toJSONString(list);
    }
}
