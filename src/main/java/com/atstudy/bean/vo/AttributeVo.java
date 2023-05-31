package com.atstudy.bean.vo;

import com.atstudy.bean.po.SpuAttrValue;

import java.util.List;

//修改商品时，界面上需要展现筛选属性select的列表信息/规格属性seldct的列表信息
public class AttributeVo {
    //key_id,key_name,key_issku,key_ishigh,createtime,updatetime
    private Integer id; //  对应的spus表中的id字段
    private String  key_id; //属性键id
    private String key_name; //属性键名称
    private Long value_id;  //属性值的编号
    private String value_name;//属性值内容
    private Byte  key_issku;    //是否规格属性,0筛选属性，1规格属性
    private String spu_attr_imgs = "[]";    //属性值相册内容，默认是空的json字符串

    private List<SpuAttrValue> spuAttrValueList;        //当前选中的规格属性键的所有属性值列表数据

    //访问器

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public Long getValue_id() {
        return value_id;
    }

    public void setValue_id(Long value_id) {
        this.value_id = value_id;
    }

    public String getValue_name() {
        return value_name;
    }

    public void setValue_name(String value_name) {
        this.value_name = value_name;
    }

    public Byte getKey_issku() {
        return key_issku;
    }

    public void setKey_issku(Byte key_issku) {
        this.key_issku = key_issku;
    }

    public String getSpu_attr_imgs() {
        return spu_attr_imgs;
    }

    public void setSpu_attr_imgs(String spu_attr_imgs) {
        this.spu_attr_imgs = spu_attr_imgs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey_name() {
        return key_name;
    }

    public void setKey_name(String key_name) {
        this.key_name = key_name;
    }

    public List<SpuAttrValue> getSpuAttrValueList() {
        return spuAttrValueList;
    }

    public void setSpuAttrValueList(List<SpuAttrValue> spuAttrValueList) {
        this.spuAttrValueList = spuAttrValueList;
    }

    //toString重写

    @Override
    public String toString() {
        return "AttributeVo{" +
                "key_id='" + key_id + '\'' +
                ", value_id=" + value_id +
                ", value_name='" + value_name + '\'' +
                ", key_name='" + key_name + '\'' +
                ", key_issku=" + key_issku +
                ", spu_attr_imgs='" + spu_attr_imgs + '\'' +
                ", id=" + id +
                '}';
    }
}
