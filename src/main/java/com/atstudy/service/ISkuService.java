package com.atstudy.service;

import com.atstudy.bean.bo.AddSkuBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SkuSearchBo;
import com.atstudy.bean.po.Sku;

import java.util.List;

//业务层接口类-负责规格业务
public interface ISkuService {
    //查数据的业务方法
    List<Sku> getList(SkuSearchBo skuSearchBo, PageBo pageBo);

    //添加sku
    boolean addOne(AddSkuBo addSkuBo);

}
