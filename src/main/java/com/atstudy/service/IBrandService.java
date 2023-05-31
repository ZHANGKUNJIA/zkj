package com.atstudy.service;

import com.atstudy.bean.bo.AddBrandBo;
import com.atstudy.bean.bo.SearchBrandBo;
import com.atstudy.bean.po.Brand;

import java.util.List;

//业务层接口类-负责品牌
public interface IBrandService {

    //通过brandid要查询一个品牌的实体信息
    Brand getOne(String brand_id);

    //添加品牌信息
    int add(AddBrandBo addBrandBo);

    //查询列表
    List<Brand> getList(SearchBrandBo searchBrandBo);


    //得到所有品牌列表的功能
    List<Brand> getAll();
}
