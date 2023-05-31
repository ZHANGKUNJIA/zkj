package com.atstudy.bean.vo;

import com.atstudy.bean.po.Brand;
import com.atstudy.bean.po.Category;

import java.util.Date;
import java.util.List;

//商品vo类
public class SpuVo {
    //成员属性
    private Long spu_id;                // 商品Spu编号
    private String spu_name;            // 商品Spu名称
    private String spu_title;           // 商品Spu标题
    private String spu_introduction;    // 商品Spu详情
    private String spu_unit;            // 商品Spu单位
    private Byte spu_status;            // 商品Spu状态 1：上架 0：下架
    private String spu_brand_id;        // 商品品牌编号
    private Date createtime;            // 创建时间
    private Date updatetime;            // 更新时间
    // 组合关系
    private Brand brand;                // 商品品牌对象

    //分类列表
    private List<Category> categoryList;

    //筛选属性列表
    private List<AttributeVo> filterList;

    //规格属性列表
    private List<AttributeVo> skuList;

    //访问器

    public Long getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Long spu_id) {
        this.spu_id = spu_id;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getSpu_title() {
        return spu_title;
    }

    public void setSpu_title(String spu_title) {
        this.spu_title = spu_title;
    }

    public String getSpu_introduction() {
        return spu_introduction;
    }

    public void setSpu_introduction(String spu_introduction) {
        this.spu_introduction = spu_introduction;
    }

    public String getSpu_unit() {
        return spu_unit;
    }

    public void setSpu_unit(String spu_unit) {
        this.spu_unit = spu_unit;
    }

    public Byte getSpu_status() {
        return spu_status;
    }

    public void setSpu_status(Byte spu_status) {
        this.spu_status = spu_status;
    }

    public String getSpu_brand_id() {
        return spu_brand_id;
    }

    public void setSpu_brand_id(String spu_brand_id) {
        this.spu_brand_id = spu_brand_id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<AttributeVo> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<AttributeVo> filterList) {
        this.filterList = filterList;
    }

    public List<AttributeVo> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<AttributeVo> skuList) {
        this.skuList = skuList;
    }


    //toString重写

    @Override
    public String toString() {
        return "SpuVo{" +
                "spu_id=" + spu_id +
                ", spu_name='" + spu_name + '\'' +
                ", spu_title='" + spu_title + '\'' +
                ", spu_introduction='" + spu_introduction + '\'' +
                ", spu_unit='" + spu_unit + '\'' +
                ", spu_status=" + spu_status +
                ", spu_brand_id='" + spu_brand_id + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", brand=" + brand +
                ", categoryList=" + categoryList +
                ", filterList=" + filterList +
                ", skuList=" + skuList +
                '}';
    }
}
