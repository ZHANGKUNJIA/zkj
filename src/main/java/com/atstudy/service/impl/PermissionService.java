package com.atstudy.service.impl;

import com.atstudy.bean.po.Permission;
import com.atstudy.bean.vo.PermissionWithRoleVo;
import com.atstudy.mapper.PermissionMapper;
import com.atstudy.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//业务层实现类-负责权限功能业务
@Service
public class PermissionService implements IPermissionService {

    //成员属性
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getAll() {
        return permissionMapper.getAll();
    }

    @Override
    public List<PermissionWithRoleVo> getListWithRoleByRoleId(Integer role_id) {

        return permissionMapper.getListWithRoleByRoleId( role_id );
    }
}
