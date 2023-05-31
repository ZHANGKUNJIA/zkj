package com.atstudy.service.impl;

import com.alibaba.fastjson.JSON;
import com.atstudy.bean.bo.AddSkuBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SkuSearchBo;
import com.atstudy.bean.po.Sku;
import com.atstudy.mapper.SkuMapper;
import com.atstudy.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//业务层实现类-负责规格业务
@Service
public class SkuService implements ISkuService {
    //上层：业务实现类；下层调用数据层接口类
    @Autowired
    SkuMapper skuMapper;

    @Override
    public List<Sku> getList(SkuSearchBo skuSearchBo, PageBo pageBo) {
        //得到符合条件的记录数
        int resultCount = skuMapper.getCount(skuSearchBo);

        //赋值给pageBo的resultCount成员属性。用于分页
        pageBo.setResultCount(resultCount);

        //返回List<Sku>
        return skuMapper.getList(skuSearchBo,pageBo);
    }

    @Override
    public boolean addOne(AddSkuBo addSkuBo) {
        //设置创建时间、修改时间
        addSkuBo.setCreatetime( new Date());
        addSkuBo.setUpdatetime(new Date());

        //设置一下json的字符串信息(skuAttrBoList->JSON字符串)
        addSkuBo.setSku_spuattr(
                JSON.toJSONString(addSkuBo.getSkuAttrBoList())
        );



        //添加数据
        int affectedRow = skuMapper.addOne(addSkuBo);

        return affectedRow>0;
    }
}
