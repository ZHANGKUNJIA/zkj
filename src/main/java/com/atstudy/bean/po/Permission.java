package com.atstudy.bean.po;

//实体模型类-权限功能的实体
public class Permission {
    //成员属性
    private Integer permission_id;      //权限功能id
    private  String permission_name;        //权限名
    private Integer pid;                    //父级的权限功能id

    //访问器

    public Integer getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Integer permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    //toString()

    @Override
    public String toString() {
        return "Permission{" +
                "permission_id=" + permission_id +
                ", permission_name='" + permission_name + '\'' +
                ", pid=" + pid +
                '}';
    }
}
