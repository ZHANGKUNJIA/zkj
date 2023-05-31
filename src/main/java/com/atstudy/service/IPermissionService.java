package com.atstudy.service;

import com.atstudy.bean.po.Permission;
import com.atstudy.bean.vo.PermissionWithRoleVo;

import java.util.List;

//业务层接口类-负责权限功能
public interface IPermissionService {

    //得到所有的权限功能列表
    List<Permission> getAll();


    // 根据角色编号 查询 可以判断角色是否拥有的权限列表
    List<PermissionWithRoleVo> getListWithRoleByRoleId(Integer role_id );
}
