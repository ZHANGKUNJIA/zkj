package com.atstudy.bean.bo;

//规格管理查询界面-查询条件
public class SkuSearchBo {
    private Long sku_id;        //规格id
    private Long sku_spu_id;    //商品编号
    private String sku_name;    //规格名

    private Double sku_min_price;   //最低单价
    private Double sku_max_price;   //最高单价

    private Double sku_min_quantity;   //最低库存
    private Double sku_max_quantity;   //最高库存

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

    public Double getSku_min_price() {
        return sku_min_price;
    }

    public void setSku_min_price(Double sku_min_price) {
        this.sku_min_price = sku_min_price;
    }

    public Double getSku_max_price() {
        return sku_max_price;
    }

    public void setSku_max_price(Double sku_max_price) {
        this.sku_max_price = sku_max_price;
    }

    public Double getSku_min_quantity() {
        return sku_min_quantity;
    }

    public void setSku_min_quantity(Double sku_min_quantity) {
        this.sku_min_quantity = sku_min_quantity;
    }

    public Double getSku_max_quantity() {
        return sku_max_quantity;
    }

    public void setSku_max_quantity(Double sku_max_quantity) {
        this.sku_max_quantity = sku_max_quantity;
    }
}
