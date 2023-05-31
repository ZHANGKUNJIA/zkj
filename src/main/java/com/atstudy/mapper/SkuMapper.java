package com.atstudy.mapper;

import com.atstudy.bean.bo.AddSkuBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SkuSearchBo;
import com.atstudy.bean.bo.SpuSearchBo;
import com.atstudy.bean.po.Sku;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//数据层接口类-负责规格业务
@Repository
public interface SkuMapper {

    //根据给定查询条件，得到记录数
    Integer getCount(@Param("skuSearchBo") SkuSearchBo skuSearchBo);

    //根据给定查询条件，返回List<Sku>
    List<Sku> getList(@Param("skuSearchBo")SkuSearchBo skuSearchBo, @Param("pageBo")PageBo pageBo);

    //新增一个sku
    int addOne(
            @Param("addSkuBo")
            AddSkuBo addSkuBo
    );
}
