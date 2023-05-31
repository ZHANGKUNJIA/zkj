package com.atstudy.bean.po;

import java.util.Date;

//po类-对应着数据表spu_attr_value
public class SpuAttrValue {
    //成员属性
    private Long id;        //属性值id
    private String value_name;  //属性值名称
    private Date createtime;    //创建时间
    private Date updatetime;    //更新时间
    private String value_attr_key_id;   //key_id，，外键

    //访问器

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue_name() {
        return value_name;
    }

    public void setValue_name(String value_name) {
        this.value_name = value_name;
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

    public String getValue_attr_key_id() {
        return value_attr_key_id;
    }

    public void setValue_attr_key_id(String value_attr_key_id) {
        this.value_attr_key_id = value_attr_key_id;
    }


    //toString的重写

    @Override
    public String toString() {
        return "SpuAttrValue{" +
                "id=" + id +
                ", value_name='" + value_name + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", value_attr_key_id='" + value_attr_key_id + '\'' +
                '}';
    }
}
