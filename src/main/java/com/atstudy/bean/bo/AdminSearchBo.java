package com.atstudy.bean.bo;

//作为员工列表查询的条件的bo类
public class AdminSearchBo {
    //成员属性

    private Integer admin_id;       //员工编号
    private String admin_name;      //员工姓名
    private  String admin_nickname; //姓名
    private Integer role_id;        //按角色编号查询

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

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }


    //toString

    @Override
    public String toString() {
        return "AdminSearchBo{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_nickname='" + admin_nickname + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}
