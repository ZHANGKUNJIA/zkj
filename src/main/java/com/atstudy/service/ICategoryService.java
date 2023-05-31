package com.atstudy.service;

import com.atstudy.bean.bo.AddCategoryBo;
import com.atstudy.bean.bo.CategorySearchBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.UpdateCategoryBo;
import com.atstudy.bean.po.Category;

import java.util.List;

//业务层接口类-负责分类业务
public interface ICategoryService {
    //通过分类id，查找1个分类实体
    Category getOne(int cate_id);

    //得到所有分类的数据
    List<Category> getAll();

    //条件查询，得到分类列表
    List<Category> getListWithParent(
            CategorySearchBo categorySearchBo, PageBo pageBo
    );

    //单选删除
    boolean deleteOne(Integer cate_id);

    //批量删除
    boolean multDel(Integer[] id_list);

    //添加1个分类
    boolean addOne(AddCategoryBo addCategoryBo);

    //通过分类id，得到一个带品牌列表+带筛选属性键列表的分类实体
    Category getOneWithBrandAndAttr(Integer cate_id);


    // 修改分类
    boolean updateOne(UpdateCategoryBo updateCategoryBo);
}
