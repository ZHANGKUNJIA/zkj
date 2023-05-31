package com.atstudy.mapper;

import com.atstudy.bean.bo.UpdateSpuBo;
import com.atstudy.bean.po.SpuAttrValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//数据访问层接口类-负责商品属性值业务
@Repository
public interface SpuAttrValueMapper {
    //抽象方法
    ////通过属性键id，得到属性值列表的功能
    List<SpuAttrValue> getListByKeyId(
            @Param("key_id")
            String key_id
    );

    //通过商品编号，删除这个商品所有属性值信息
    int deleteSpuAttrValueRelationBySpuId(
            @Param("spu_id")
            Long spu_id
    );

    //批量添加某个商品的，筛选属性值列表数据
    int addSpuAttrValueFilterRelation(
            @Param("updateSpuBo")UpdateSpuBo updateSpuBo
            );

    //批量添加某个商品的，规格属性值列表数据
    int addSpuAttrValueSkuRelation(
            @Param("updateSpuBo")UpdateSpuBo updateSpuBo
    );
}
