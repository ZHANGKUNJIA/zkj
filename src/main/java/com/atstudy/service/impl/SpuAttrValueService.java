package com.atstudy.service.impl;

import com.atstudy.bean.po.SpuAttrValue;
import com.atstudy.mapper.SpuAttrValueMapper;
import com.atstudy.service.ISpuAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//业务层实现类-属性值业务
@Service
public class SpuAttrValueService implements ISpuAttrValueService {
    //需要调用下层，数据访问层SpuAttrValueMapper
    @Autowired
    SpuAttrValueMapper spuAttrValueMapper;


    @Override
    public List<SpuAttrValue> getListByKeyId(String key_id) {
        return spuAttrValueMapper.getListByKeyId(key_id);
    }
}
