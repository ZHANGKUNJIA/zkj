package com.atstudy.bean.bo;

import com.atstudy.bean.po.Brand;
import com.atstudy.bean.po.SpuAttrKey;

import java.sql.Timestamp;
import java.util.List;

//修改分类-业务模型类
public class UpdateCategoryBo {
    //成员属性
    private Integer cate_id;        //分类id
    private String cate_name;       //分类名称
    private Integer cate_sort;      //排序字段
    private Timestamp createtime;   //创建时间（数据库中是timestamp类型）
    private Timestamp updatetime;   //修改时间
    private Integer cate_parentid;  //父级分类id
    private String cate_channel="";    //初始就是空字符串

    //，还需要增加其他成员属性

    //还需要增加其他成员属性
    private List<Brand> brandList;      //分类-品牌列表
    //需要提交到brand_categories表

    private List<SpuAttrKey> spuAttrKeyList;    //分类-筛选属性键列表
    //需要批量提交到spu_attr_key_categories表


    //访问器

    public Integer getCate_id() {
        return cate_id;
    }

    public void setCate_id(Integer cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public Integer getCate_sort() {
        return cate_sort;
    }

    public void setCate_sort(Integer cate_sort) {
        this.cate_sort = cate_sort;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getCate_parentid() {
        return cate_parentid;
    }

    public void setCate_parentid(Integer cate_parentid) {
        this.cate_parentid = cate_parentid;
    }

    public String getCate_channel() {
        return cate_channel;
    }

    public void setCate_channel(String cate_channel) {
        this.cate_channel = cate_channel;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<SpuAttrKey> getSpuAttrKeyList() {
        return spuAttrKeyList;
    }

    public void setSpuAttrKeyList(List<SpuAttrKey> spuAttrKeyList) {
        this.spuAttrKeyList = spuAttrKeyList;
    }

    //重写toString方法

    @Override
    public String toString() {
        return "UpdateCategoryBo{" +
                "cate_id=" + cate_id +
                ", cate_name='" + cate_name + '\'' +
                ", cate_sort=" + cate_sort +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", cate_parentid=" + cate_parentid +
                ", cate_channel='" + cate_channel + '\'' +
                ", brandList=" + brandList +
                ", spuAttrKeyList=" + spuAttrKeyList +
                '}';
    }
}
