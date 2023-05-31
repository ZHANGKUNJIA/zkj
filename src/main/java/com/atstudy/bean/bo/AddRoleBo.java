package com.atstudy.bean.bo;

import java.util.Arrays;

//实体类-负责角色添加
public class AddRoleBo {
    //role表的数据
    private Integer role_id;        //角色编号
    private String role_name;       //角色名称

    //role_permission表的数据的批量添加（可以创建Permission的对象，批量去插入List<Permisstion>)
    private Integer[] id_list;      //界面上批量选中的权限功能id列表

    //访问器

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


    //toString()重写


    @Override
    public String toString() {
        return "AddRoleBo{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", id_list=" + Arrays.toString(id_list) +
                '}';
    }
}
