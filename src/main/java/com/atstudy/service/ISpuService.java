package com.atstudy.service;

import com.atstudy.bean.bo.AddSpuBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SpuSearchBo;
import com.atstudy.bean.bo.UpdateSpuBo;
import com.atstudy.bean.po.Spu;
import com.atstudy.bean.vo.SpuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//业务层接口类-负责商品业务
public interface ISpuService {

    //条件查询，符合条件的商品列表（带出Brand品牌的信息）
    List<Spu> getListWithBrand(
            SpuSearchBo spuSearchBo,
            PageBo pageBo
    );

    //通过商品编号，查询到spuvo的实例
    SpuVo getVoBySpuId(Long spu_id);



    //修改商品的业务方法
    boolean updateOne(UpdateSpuBo updateSpuBo);


    //通过商品编号，查询到spu主表的实例
    Spu getOne(Long spu_id);


    // 添加商品Spu
    boolean addOne(AddSpuBo addSpuBo);

    //得到所有的商品列表
    List<Spu> getAll();
}
