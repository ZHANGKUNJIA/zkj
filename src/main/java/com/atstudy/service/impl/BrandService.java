package com.atstudy.service.impl;

import com.atstudy.bean.bo.AddBrandBo;
import com.atstudy.bean.bo.SearchBrandBo;
import com.atstudy.bean.po.Brand;
import com.atstudy.mapper.BrandMapper;
import com.atstudy.service.IBrandService;
import com.atstudy.utils.ImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.UUID;

//业务层实现类-负责品牌业务
@Service                //需要托管给spring框架，被其他人使用调用时需要由spring的context对象来自动实例化
public class BrandService
                implements IBrandService {

    //成员属性（需要调用下层--BrandMapper）
    @Autowired          //需要由spring框架来自动实例化
    BrandMapper brandMapper;

    @Autowired
    ImgUtils imgUtils;


    //成员方法
    @Override
    public Brand getOne(String brand_id) {
        return brandMapper.getOne(brand_id);
    }

    //添加品牌
    //界面上需要录入品牌名、品牌介绍、排序字段
    @Override
    public int add(AddBrandBo addBrandBo) {
        int status = -1 ;           //-1代表失败；大于0代表添加成功
        addBrandBo.setBrand_id(UUID.randomUUID().toString());       //使用UUID类来生成，brand_id，36字节的随机字符串

        //品牌添加-图片上传业务开始
        //判断brand_image成员属性的image类型
        if( !addBrandBo.getBrand_image().getContentType().startsWith("image") ){
            return status;
        }

        //判断brand_image成员属性的文件大小（10M以内）是否超标
        if(addBrandBo.getBrand_image().getSize()>= 10 * 1024 * 1024 ){
            return status;
        }

        //调用统一的图片上传方法，返回上传的文件名
        String filename = imgUtils.upload(addBrandBo.getBrand_image());

        //把随机生成的文件名要更新到成员属性，brand_logourl
        addBrandBo.setBrand_logourl(filename);

        return brandMapper.add(addBrandBo);
    }

    @Override
    public List<Brand> getList(SearchBrandBo searchBrandBo) {
        return brandMapper.getList(searchBrandBo);
    }

    @Override
    public List<Brand> getAll() {
        return brandMapper.getAll();
    }

}
