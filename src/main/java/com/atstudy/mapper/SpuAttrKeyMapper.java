package com.atstudy.mapper;

import com.atstudy.bean.bo.AddCategoryBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SpuAttrKeySearchBo;
import com.atstudy.bean.po.SpuAttrKey;
import com.atstudy.bean.vo.AttributeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//数据层接口类-负责属性键的业务
public interface SpuAttrKeyMapper {

    //得到所有筛选键属性列表
    List<SpuAttrKey> getFilterAll();

    //得到所有规格属性键列表
    List<SpuAttrKey> getSkuAll();

    //批量添加筛选属性键-分类数据
    int multiAddCateAttr(
            @Param("addCategoryBo")
            AddCategoryBo addCategoryBo);

    //通过cate_id，得到筛选属性键的数据
    List<SpuAttrKey> getListByCate(
            @Param("cate_id")
            Integer cate_id
    );


    //删除-分类-筛选属性键关联表的数据
    int delCateAttrKey(@Param("cate_id") Integer cate_id);

    //批量删除-分类-筛选属性键关联表的数据
    int multiDelAttrKey(@Param("id_list") Integer[] id_list);

    //按照查询条件，找属性键列表的数据
    List<SpuAttrKey> getList(
            @Param("spuAttrKeySearchBo") SpuAttrKeySearchBo spuAttrKeySearchBo,
            @Param("pageBo") PageBo pageBo);

    //按照查询条件，得到符合条件的记录数，用于分页
    Integer getCount(@Param("spuAttrKeySearchBo") SpuAttrKeySearchBo spuAttrKeySearchBo);

    //根据商品id，查询这个商品的关联，筛选属性信息
    List<AttributeVo> getFiltersBySpuId(
            @Param("spu_id")Long spu_id
    );

    //根据商品id，查询这个商品的关联，规格属性信息
    List<AttributeVo> getSkusBySpuId(
            @Param("spu_id")Long spu_id
    );

    //根据商品id，查询这个商品的关联，规格属性信息（带出，该规格属性键，的所有规格属性值列表数据）
    List<AttributeVo> getSkusWithValueListBySpuId(
            @Param("spu_id")Long spu_id
    );
}
