package com.atstudy.service;

import com.atstudy.bean.po.SpuAttrValue;

import java.util.List;

//业务层接口类-负责商品属性值业务
public interface ISpuAttrValueService {
    //抽象方法
    //通过属性键id，得到属性值列表的功能
    List<SpuAttrValue> getListByKeyId(String key_id);
}
