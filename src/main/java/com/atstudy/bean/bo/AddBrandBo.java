package com.atstudy.bean.bo;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

//添加品牌界面bo类
public class AddBrandBo {
    //成员属性

    private String brand_id;    //品牌编号（需要从service层自动生成1个不重复）

    private String brand_name;  //品牌名称（从界面上自动添加）
    private String brand_introduction;  //介绍（从界面上自动添加）

    //增加一个成员属性，MultipartFile,用于接收表单中的图片流
    private MultipartFile brand_image;  //起名brand_image才能和/brand/add.html中的控件对应上

    private String brand_logourl;   //此处存放的是图片的url路径,（要从service层添加一个默认的url）

    private Integer sortno;         //展现时的排序字段
    private Date createtime=new Date();        //创建时间，实例化时候的时间
    private Date updatetime=new Date();        //修改时间，实例化时候的时间

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

    public void setBrand_name (String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_introduction() {
        return brand_introduction;
    }

    public void setBrand_introduction(String brand_introduction) {
        this.brand_introduction = brand_introduction;
    }

    public String getBrand_logourl() {
        return brand_logourl;
    }

    public void setBrand_logourl(String brand_logourl) {
        this.brand_logourl = brand_logourl;
    }

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
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


    public MultipartFile getBrand_image() {
        return brand_image;
    }

    public void setBrand_image(MultipartFile brand_image) {
        this.brand_image = brand_image;
    }
//toString的重写

    @Override
    public String toString() {
        return "AddBrandBo{" +
                "brand_id='" + brand_id + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", brand_introduction='" + brand_introduction + '\'' +
                ", brand_image=" + brand_image +
                ", brand_logourl='" + brand_logourl + '\'' +
                ", sortno=" + sortno +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}
