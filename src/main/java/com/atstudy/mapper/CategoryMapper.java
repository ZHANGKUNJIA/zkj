package com.atstudy.mapper;

import com.atstudy.bean.bo.*;
import com.atstudy.bean.po.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//数据访问层接口类-负责分类业务
@Repository         //代表托管给spring框架
public interface CategoryMapper {
    //抽象方法
    //通过分类的id，查询1个分类实体信息
    Category getOne(
            @Param("cate_id")       //需要将当前修饰的入参cate_id的值，传递给xml文件中sql语句中对应的cate_id的变量
            int cate_id
    );

    //添加分类
    int addOne(
            @Param("addCategoryBo")
            AddCategoryBo addCategoryBo       //AddCategoryBo类
    );

    //修改分类
    int updateOne(
            @Param("updateCategoryBo")
            UpdateCategoryBo updateCategoryBo
    );

    //删除1条分类
    int deleteOne(
            @Param("cate_id")
            Integer cate_id
    );

    //条件查询，返回分类列表的数据
    List<Category> getListWithParent(
            @Param("categorySearchBo")
            CategorySearchBo categorySearchBo,
            @Param("pageBo")
            PageBo pageBo
    );

    //条件查询，得到记录数
    Integer getCount(@Param("categorySearchBo")
             CategorySearchBo categorySearchBo);

    //得到所有分类列表
    List<Category> getAll();

    //批量删除分类信息
    int multiDel(
            @Param("id_list")
            Integer[] id_list);

    //得到一个带品牌列表&筛选属性键列表的分类实体
    Category getOneWithBrandAndAttr(
            @Param("cate_id")
            int cate_id
    );

    //通过商品编号，得到这个商品关联的分类列表信息
    List<Category> getListBySpuId(
            @Param("spu_id")
            Long spu_id
    );

    //删除商品-分类的数据
    int deleteSpuCate(@Param("spu_id")Long spu_id);

    //批量添加-商品和分类的数据
    int addSpuCate(@Param("updateSpuBo") UpdateSpuBo updateSpuBo);
}
