package com.atstudy.mapper;

import com.atstudy.bean.bo.AddSpuBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SpuSearchBo;
import com.atstudy.bean.bo.UpdateSpuBo;
import com.atstudy.bean.po.Spu;
import com.atstudy.bean.vo.SpuVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//数据访问层接口类-负责商品业务
@Repository
public interface SpuMapper {

    // 查询 满足条件的总记录数
    Integer getCount( @Param("spuSearchBo") SpuSearchBo spuSearchBo);

    // 查询 满足条件的 含有品牌信息 的 商品Spu列表
    List<Spu> getListWithBrand(
            @Param("spuSearchBo") SpuSearchBo spuSearchBo,
            @Param("pageBo") PageBo pageBo
    );

    //根据商品编号，得到1个商品的vo对象
    SpuVo getVo(
            @Param("spu_id")
            Long spu_id);

    //更新商品主表信息
    int updateOne(
            @Param("updateSpuBo")
            UpdateSpuBo updateSpuBo
    );

    //根据商品编号，返回1个商品主表信息
    Spu getOne(
            @Param("spu_id")
            Long spu_id);


    // 添加一个商品Spu
    int addOne(@Param("addSpuBo") AddSpuBo addSpuBo);

    //得到所有商品列表
    List<Spu> getAll();
}
