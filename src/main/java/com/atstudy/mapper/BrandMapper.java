package com.atstudy.mapper;

import com.atstudy.bean.bo.AddBrandBo;
import com.atstudy.bean.bo.AddCategoryBo;
import com.atstudy.bean.bo.SearchBrandBo;
import com.atstudy.bean.po.Brand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//数据访问层接口类，负责品牌业务
@Repository                 //需要托管给spring框架，被调用时由spring框架的context来实例化
public interface BrandMapper {

    //根据一个品牌id要查询品牌的实体信息
    Brand getOne(
            @Param("brand_id")      //当前的入参，需要和brandmapper.xml中的sql要数据交互
            String brand_id);

    //添加品牌........适配于添加品牌的界面
    int add(
            @Param("addBrandBo")
            AddBrandBo addBrandBo
    );


    //按照某种查询条件要查询品牌列表...适配于品牌查询的界面
    List<Brand> getList(
            @Param("searchBrandBo")             //需要和xml中的sql语句参数要做数据传递
            SearchBrandBo searchBrandBo);       //写法2

    //todo，
    //修改品牌信息
    //根据品牌id，删除1条品牌信息
    //批量删除品牌信息

    List<Brand> getAll();

    //批量添加分类-品牌关联表的数据
    int multiAddCateBrand(
            @Param("addCategoryBo")
            AddCategoryBo addCategoryBo);

    //通过cate_id，得到品牌列表的数据.
    List<Brand> getListByCate(
            @Param("cate_id")
            Integer cate_id
    );

    //删除-分类-品牌关联表的数据
    int delCateBrand(@Param("cate_id") Integer cate_id);

    //批量删除-分类-品牌关联表的数据
    int multiDelCateBrand(@Param("id_list") Integer[] id_list);
}
