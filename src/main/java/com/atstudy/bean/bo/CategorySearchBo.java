package com.atstudy.bean.bo;

//分类管理页面的查询条件-业务类
public class CategorySearchBo {
    private Integer cate_id;        //分类id
    private String cate_name;       //分类名称
    private Integer cate_parentid;  //父级分类id

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

    public Integer getCate_parentid() {
        return cate_parentid;
    }

    public void setCate_parentid(Integer cate_parentid) {
        this.cate_parentid = cate_parentid;
    }

    //toString重写

    @Override
    public String toString() {
        return "CategorySearchBo{" +
                "cate_id=" + cate_id +
                ", cate_name='" + cate_name + '\'' +
                ", cate_parentid=" + cate_parentid +
                '}';
    }
}
