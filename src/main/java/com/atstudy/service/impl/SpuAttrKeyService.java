package com.atstudy.service.impl;

import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SpuAttrKeySearchBo;
import com.atstudy.bean.po.SpuAttrKey;
import com.atstudy.bean.vo.AttributeVo;
import com.atstudy.mapper.SpuAttrKeyMapper;
import com.atstudy.service.ISpuAttrKeyService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//业务层实现类-负责属性键业务
@Service
public class SpuAttrKeyService implements ISpuAttrKeyService {
    //成员属性

    @Autowired
    SpuAttrKeyMapper spuAttrKeyMapper;


    @Override
    public List<SpuAttrKey> getFilterAll() {
        return spuAttrKeyMapper.getFilterAll();
    }

    @Override
    public List<SpuAttrKey> getSkuAll() {
        return spuAttrKeyMapper.getSkuAll();
    }

    //用mysql-limit子句来实现的分页
//    @Override
//    public List<SpuAttrKey> getList(SpuAttrKeySearchBo spuAttrKeySearchBo, PageBo pageBo) {
//        /*分页方案1，用mapper.getList中的分页,sql中已经写了limit的语句来做*/
//        //得到记录数resultCount
//        Integer resultCount = spuAttrKeyMapper.getCount(spuAttrKeySearchBo);
//
//        //设置pageBo对象的记录数
//        pageBo.setResultCount(resultCount);
//
//        //调用getList的方法
//        List<SpuAttrKey> list = spuAttrKeyMapper.getList(spuAttrKeySearchBo,pageBo);
//
//        //list = list.subList(0,15);
//
//        return list;
//    }

    //使用Pagehelper来实现的分页
    @Override
    public List<SpuAttrKey> getList(SpuAttrKeySearchBo spuAttrKeySearchBo, PageBo pageBo) {
        //第一步，调用pagehelper，设置每页显示记录数+当前是第几页，这两个参数【拦截】
        PageHelper.startPage(pageBo.getPage(),pageBo.getPageSize());

        //第二步，调用getList的方法(limit子句已经注释掉，会由pagehelper以拦截器/aop的方式自动给sql加上limit)
        List<SpuAttrKey> list = spuAttrKeyMapper.getList(spuAttrKeySearchBo,new PageBo());

        //第三步，得到记录数
        Page page = (Page)list;

        //得到记录数resultCount
        Integer resultCount =(int) page.getTotal();
        //设置pageBo对象的记录数
        pageBo.setResultCount(resultCount);

        return list;
    }

    @Override
    public List<AttributeVo> getSkusBySpuId(Long spu_id) {
        return spuAttrKeyMapper.getSkusWithValueListBySpuId(spu_id);
    }


}
