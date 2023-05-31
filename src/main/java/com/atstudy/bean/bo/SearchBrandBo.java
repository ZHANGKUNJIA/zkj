package com.atstudy.bean.bo;

//bo类---负责品牌查询界面的入参条件
public class SearchBrandBo {

    //成员属性
    private String brand_id;    //品牌编号
    private String brand_name;  //品牌名称

    //访问器

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    //toString重写

    @Override
    public String toString() {
        return "SearchBrandBo{" +
                "brand_id='" + brand_id + '\'' +
                ", brand_name='" + brand_name + '\'' +
                '}';
    }
}
