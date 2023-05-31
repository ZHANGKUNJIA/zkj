package com.atstudy.bean.bo;

//查询条件业务类-负责商品管理页面 的查询条件
public class SpuSearchBo {
    //4个查询条件
    private Long spu_id;                // 商品Spu编号
    private String spu_name;            // 商品Spu名称
    private Byte spu_status;            // 商品Spu状态 0：上架 1：下架
    private String spu_brand_id;        // 商品品牌编号

    //增加分页参数
    private PageBo pageBo;

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

    public PageBo getPageBo() {
        return pageBo;
    }

    public void setPageBo(PageBo pageBo) {
        this.pageBo = pageBo;
    }

    //toString重写

    @Override
    public String toString() {
        return "SpuSearchBo{" +
                "spu_id=" + spu_id +
                ", spu_name='" + spu_name + '\'' +
                ", spu_status=" + spu_status +
                ", spu_brand_id='" + spu_brand_id + '\'' +
                ", pageBo=" + pageBo +
                '}';
    }
}
