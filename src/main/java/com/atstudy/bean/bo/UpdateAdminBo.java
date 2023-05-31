package com.atstudy.bean.bo;

import com.atstudy.bean.po.Role;

import java.util.List;

//业务模型-负责修改员工的业务界面
public class UpdateAdminBo {

    private Integer admin_id;       //账号id（界面上没有这个字段）
    private String admin_name;      //账号名称
    private String  admin_nickname; //员工姓名
    private List<Role> roleList;              //1个员工  对应的 角色列表

    //访问器

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }


    public String getAdmin_nickname() {
        return admin_nickname;
    }

    public void setAdmin_nickname(String admin_nickname) {
        this.admin_nickname = admin_nickname;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    //toString方法重写

    @Override
    public String toString() {
        return "UpdateAdminBo{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_nickname='" + admin_nickname + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
