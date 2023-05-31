package com.atstudy.service;

import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SpuAttrKeySearchBo;
import com.atstudy.bean.po.Spu;
import com.atstudy.bean.po.SpuAttrKey;
import com.atstudy.bean.vo.AttributeVo;

import java.util.List;

//业务层接口类-负责属性键业务
public interface ISpuAttrKeyService {

    //得到所有筛选属性键的列表数据
    List<SpuAttrKey> getFilterAll();

    //得到所有规格属性键的列表数据
    List<SpuAttrKey> getSkuAll();

    //按照查询条件，得到属性键的列表数据
    List<SpuAttrKey> getList(SpuAttrKeySearchBo spuAttrKeySearchBo, PageBo pageBo);

    //根据给定的商品编号，要查询这个商品的所有规格属性键信息（带出该规格属性键的所有规格属性值列表数据）
    List<AttributeVo> getSkusBySpuId(Long spu_id);
}
