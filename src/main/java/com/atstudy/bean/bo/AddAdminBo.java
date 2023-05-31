package com.atstudy.bean.bo;

import com.atstudy.bean.po.Role;

import java.util.List;

//业务模型-负责添加员工的业务界面（字段设置除了要考虑界面因素之外，也要考虑交互数据表的字段因素）
public class AddAdminBo {

    private Integer admin_id;       //账号id（界面上没有这个字段）
    private String admin_name;      //账号名称
    private String admin_pass;      //账号密码（界面上没有这个字段）
    private String  admin_nickname; //员工姓名
    private List<Role> roleList;              //1个员工  对应的 角色列表
    //AddAdminBo类型，是需要在AdminMapper.xml中，需要和admin数据表+admin_role数据表直接交互、有insert的动作
    /**
     * INSERT INTO atstudy_mall.admin
     * (admin_name, admin_pass, admin_nickname)
     * VALUES('', '', '');
     */
    //如果AddAdminBo类型中，没有admin_pass字段，需要怎么进行数据字段的插入呢？——是否需要AddAdminBo入参之外，增加1个
    //String admin_pass作为第2个入参？。。。。数据访问层、实体层的接口方法设计的不太合理

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

    public String getAdmin_pass() {
        return admin_pass;
    }

    public void setAdmin_pass(String admin_pass) {
        this.admin_pass = admin_pass;
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
        return "AddAdminBo{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_pass='" + admin_pass + '\'' +
                ", admin_nickname='" + admin_nickname + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
