package com.atstudy.bean.bo;

import com.atstudy.bean.vo.AttributeVo;

import java.util.Date;
import java.util.List;

//添加一个规格实体
public class AddSkuBo {
    private Long sku_id;        //规格id，自增
    private Long sku_spu_id;    //商品编号
    private String sku_name;    //规格名
    private String sku_spuattr; //商品规格的规格属性组合的方案。json字符串
                                //并不是由用户直接录入的，从界面中要根据用户选择的规格属性值的信息要拼接得来的
                                //只需要5个属性(spu_id/key_id/key_name/value_id/value_name)->新增一个新的Bo

    //List<规格属性值>....商品编号,属性键id,属性键名,属性值id，属性值名称
    private List<SkuAttrBo> skuAttrBoList;

    private Double sku_price;   //单价
    private Double sku_originalprice;   //原价
    private Double sku_costprice;   //折扣价

    private Integer sku_quantity;   //数量
    private Integer sortno;         //排序字段

    private Date createtime;        //创建时间
    private Date updatetime;        //更新时间

    //访问器

    public Long getSku_id() {
        return sku_id;
    }

    public void setSku_id(Long sku_id) {
        this.sku_id = sku_id;
    }

    public Long getSku_spu_id() {
        return sku_spu_id;
    }

    public void setSku_spu_id(Long sku_spu_id) {
        this.sku_spu_id = sku_spu_id;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public String getSku_spuattr() {
        return sku_spuattr;
    }

    public void setSku_spuattr(String sku_spuattr) {
        this.sku_spuattr = sku_spuattr;
    }

    public List<SkuAttrBo> getSkuAttrBoList() {
        return skuAttrBoList;
    }

    public void setSkuAttrBoList(List<SkuAttrBo> skuAttrBoList) {
        this.skuAttrBoList = skuAttrBoList;
    }

    public Double getSku_price() {
        return sku_price;
    }

    public void setSku_price(Double sku_price) {
        this.sku_price = sku_price;
    }

    public Double getSku_originalprice() {
        return sku_originalprice;
    }

    public void setSku_originalprice(Double sku_originalprice) {
        this.sku_originalprice = sku_originalprice;
    }

    public Double getSku_costprice() {
        return sku_costprice;
    }

    public void setSku_costprice(Double sku_costprice) {
        this.sku_costprice = sku_costprice;
    }

    public Integer getSku_quantity() {
        return sku_quantity;
    }

    public void setSku_quantity(Integer sku_quantity) {
        this.sku_quantity = sku_quantity;
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

    //toString方法

    @Override
    public String toString() {
        return "AddSkuBo{" +
                "sku_id=" + sku_id +
                ", sku_spu_id=" + sku_spu_id +
                ", sku_name='" + sku_name + '\'' +
                ", sku_spuattr='" + sku_spuattr + '\'' +
                ", skuAttrBoList=" + skuAttrBoList +
                ", sku_price=" + sku_price +
                ", sku_originalprice=" + sku_originalprice +
                ", sku_costprice=" + sku_costprice +
                ", sku_quantity=" + sku_quantity +
                ", sortno=" + sortno +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}
