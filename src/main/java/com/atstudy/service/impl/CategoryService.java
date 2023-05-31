package com.atstudy.service.impl;

import com.alibaba.fastjson.JSON;
import com.atstudy.bean.bo.AddCategoryBo;
import com.atstudy.bean.bo.CategorySearchBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.UpdateCategoryBo;
import com.atstudy.bean.po.Category;
import com.atstudy.mapper.BrandMapper;
import com.atstudy.mapper.CategoryMapper;
import com.atstudy.mapper.SpuAttrKeyMapper;
import com.atstudy.service.ICategoryService;
import com.atstudy.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

//业务层实现类-负责分类业务
@Service            //代表将当前类型托管给spring框架/将当前类型转换成springbean
public class CategoryService implements ICategoryService {

    //成员属性,下层对象是数据访问层CategoryMapper接口类型(Autowired/Resource注解二选一)
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    SpuAttrKeyMapper spuAttrKeyMapper;

    //需要调用redis
    @Autowired
    RedisUtils redisUtils;

    @Override
    public Category getOne(int cate_id) {
        String key = "cate-"+cate_id;
        Category category;
        String json ="";

        //第一步，从redis中取key
        if(redisUtils.get(key)!=null){
            //先从json字符串转成java对象，再返回数据
            json =  redisUtils.get(key).toString();

            category = JSON.parseObject(json,Category.class);       //参考08_JSon解析.md笔记
        }
        else {
            //没有数据

            //第二步，从mysql中取数据
            category =categoryMapper.getOne(cate_id);

            if(category!=null){
                //数据非空，第三步，把key值存到redis中
                //先把java对象转成json字符串，再set key
                json = JSON.toJSONString(category);                 //参考08_JSon解析.md笔记

                redisUtils.set(key,json,15*60);
            }
        }

        return category;
    }

    @Override
    public List<Category> getAll() {
        return categoryMapper.getAll();
    }

    @Override
    public List<Category> getListWithParent(CategorySearchBo categorySearchBo, PageBo pageBo) {
        //得到符合条件的记录数
        Integer resultCount = categoryMapper.getCount(categorySearchBo);

        //赋值给pageBo中的resultCount成员属性
        pageBo.setResultCount(resultCount);

        //查List数据

        return categoryMapper.getListWithParent(categorySearchBo,pageBo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteOne(Integer cate_id) {
        //第一步，删除主表数据
        int affectedRow = categoryMapper.deleteOne(cate_id);
        if(affectedRow<=0) {
            return false;       //失败
        }

        //第二步，删除分类-品牌表的关联数据
        int affectedRows2 = brandMapper.delCateBrand(cate_id);
//        if(affectedRows2<=0) {
//            //抛出异常->触发@Transactional的回滚rollback->收益：保证数据一致性
//            throw new RuntimeException(
//                    "删除分类，调用brandMapper.delCateBrand，返回="+affectedRows2
//            );
//        }

        //第三步，删除分类-筛选属性键的关联数据
        int affectedRows3 = spuAttrKeyMapper.delCateAttrKey(cate_id);
//        if(affectedRows3<=0){
//            //抛异常，要回滚
//            throw new RuntimeException(
//                    "删除分类，调用spuAttrKeyMapper.delCateAttrKey，返回="+affectedRows3
//            );
//        }

        return affectedRow>0;//成功返回
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean multDel(Integer[] id_list) {
        int affectedRows = categoryMapper.multiDel(id_list);

        //第二步，删除分类-品牌表的关联数据
        int affectedRows2 = brandMapper.multiDelCateBrand(id_list);

        //第三步，删除分类-筛选属性键的关联数据
        int affectedRows3 = spuAttrKeyMapper.multiDelAttrKey(id_list);

        return affectedRows>0;
    }

    @Transactional(rollbackFor = Exception.class)   //开启spring事务，出现异常时回滚
    @Override
    public boolean addOne(AddCategoryBo addCategoryBo) {
        //设置bo的创建、修改时间
        addCategoryBo.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        addCategoryBo.setCreatetime(new Timestamp(System.currentTimeMillis()));

        //第一步的db操作，添加分类主表
        int affectedRows = categoryMapper.addOne(addCategoryBo);

        //判断受影响行数大于0，并且分类实体的cate_id大于0...代表成功
        if(affectedRows>0
                && addCategoryBo.getCate_id()!=null
                && addCategoryBo.getCate_id()>0){
            //成功
            //第二步的db操作，批量添加分类-品牌的数据
            int affectedRows2 = brandMapper.multiAddCateBrand(addCategoryBo) ;

            if(affectedRows2>0) {
                //判断受影响行数大于0....代表成功
                //第三步的db操作，批量添加分类-筛选属性键的数据
                int affectedRows3 = spuAttrKeyMapper.multiAddCateAttr(addCategoryBo);

                if(affectedRows3>0) {
                    //判断受影响行数大于0....代表成功
                    return true;
                }else {
                    //第3步失败，就抛异常回滚事务
                    throw  new RuntimeException(
                            "调用spuAttrKeyMapper.multiAddCateAttr返回失败,result="+affectedRows3);
                }
            }else {
                //第2步失败，就抛异常，回滚事务
                throw  new RuntimeException(
                        "调用brandMapper.multiAddCateBrand返回失败,result="+affectedRows2);
            }
        }else {
            //失败
            return false;
        }
    }

    @Override
    public Category getOneWithBrandAndAttr(Integer cate_id) {
        return categoryMapper.getOneWithBrandAndAttr(cate_id);
    }


    // 修改分类
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOne(UpdateCategoryBo updateCategoryBo) {

        // 设置 要修改的分类对象的 更新时间
        updateCategoryBo.setUpdatetime( new Timestamp(System.currentTimeMillis()) );

        // 修改 category 数据表
        int affectedRows1 = categoryMapper.updateOne( updateCategoryBo );

        // 准备 分类添加业务模型对象
        AddCategoryBo categoryAddBo = new AddCategoryBo();
        categoryAddBo.setCate_id( updateCategoryBo.getCate_id() );
        categoryAddBo.setBrandList( updateCategoryBo.getBrandList() );
        categoryAddBo.setSpuAttrKeyList( updateCategoryBo.getSpuAttrKeyList() );

        //删除del redis key
        String key = "cate-"+updateCategoryBo.getCate_id();
        redisUtils.del(key);

        return affectedRows1 > 0 ;
    }
}
