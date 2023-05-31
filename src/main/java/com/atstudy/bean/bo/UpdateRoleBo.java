package com.atstudy.bean.bo;

/**
 * 修改角色业务模型类
 * */
public class UpdateRoleBo {

    private Integer role_id;
    private String role_name;
    private Integer[] id_list;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Integer[] getId_list() {
        return id_list;
    }

    public void setId_list(Integer[] id_list) {
        this.id_list = id_list;
    }
}
