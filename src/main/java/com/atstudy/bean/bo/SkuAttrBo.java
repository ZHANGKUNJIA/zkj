package com.atstudy.bean.bo;

//要存到sku表中的，规格属性 的json对象
public class SkuAttrBo {
    private Long spu_id;        //商品id
    private String  key_id; //属性键id
    private String key_name; //属性键名称
    private Long value_id;  //属性值的编号
    private String value_name;//属性值内容

    //访问器


    public Long getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Long spu_id) {
        this.spu_id = spu_id;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getKey_name() {
        return key_name;
    }

    public void setKey_name(String key_name) {
        this.key_name = key_name;
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

    @Override
    public String toString() {
        return "SkuAttrBo{" +
                "spu_id=" + spu_id +
                ", key_id='" + key_id + '\'' +
                ", key_name='" + key_name + '\'' +
                ", value_id=" + value_id +
                ", value_name='" + value_name + '\'' +
                '}';
    }
}
