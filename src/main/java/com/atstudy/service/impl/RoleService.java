package com.atstudy.service.impl;

import com.atstudy.bean.bo.AddRoleBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.RoleSearchBo;
import com.atstudy.bean.bo.UpdateRoleBo;
import com.atstudy.bean.po.Role;
import com.atstudy.mapper.RoleMapper;
import com.atstudy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//业务层实现类-负责角色的业务
@Service                //代表它是一个需要托管类spring框架的service实现类
public class RoleService  implements IRoleService {

    //成员属性，上层类是当前RoleService业务层实现类；下层是数据层接口类RoleMapper接口
    @Autowired          //需要由spring框架，实例化这个下层成员属性
    RoleMapper roleMapper;


    @Override
    public List<Role> getAll() {
        return roleMapper.getAll();
    }

    @Override
    public List<Role> getList(RoleSearchBo roleSearchBo,PageBo pageBo) {

        //第一步，要得到记录数,getCount()
        Integer resultCount = roleMapper.getCount(roleSearchBo);

        //第二步，设置pageBo对象上的，总记录数
        pageBo.setResultCount(resultCount);

        //第三步，调用getList，返回结果
        return roleMapper.getList(roleSearchBo,pageBo);
    }

    @Override
    public boolean addOne(AddRoleBo addRoleBo) {

        // 添加角色到 role数据表 生成角色编号 role_id
        int affectedRows1 = roleMapper.addOne( addRoleBo );

        // 添加角色权限关系数据 到 role_permission数据表
        int affectedRows2 = roleMapper.multiAddRolePermission( addRoleBo );

        return affectedRows1 > 0 && affectedRows2 > 0;
    }


    // 删除单个角色
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOne(Integer role_id) {

        // 先 删除 role数据表中的数据
        int affectedRows1 = roleMapper.deleteOne( role_id );

        // 删除该角色的权限关系
        int affectedRows2 = roleMapper.deleteRolePermissionByRoleId( role_id );

        return affectedRows1 > 0;
    }

    // 批量删除多个角色
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean multiDelete(Integer[] id_list) {

        // 先批量删除role角色表
        int affectedRows1 = roleMapper.multiDelete( id_list );

        // 删除角色的权限关系
        int affectedRows2 = roleMapper.multiDeleteRolePermission( id_list );

        return affectedRows1 > 0 ;
    }

    @Override
    public Role getOne(Integer role_id) {
        return roleMapper.getOne(role_id);
    }

    @Override
    public boolean update(UpdateRoleBo updateRoleBo) {

        // 先 修改 role 数据表
        int affectedRows1 = roleMapper.putOne( updateRoleBo );

        // 删除该角色的所有权限
        roleMapper.deleteRolePermissionByRoleId( updateRoleBo.getRole_id() );

        // 给该角色批量添加权限
        AddRoleBo bo = new AddRoleBo();
        bo.setRole_id( updateRoleBo.getRole_id() );
        bo.setId_list( updateRoleBo.getId_list() );
        int affectedRows2 = roleMapper.multiAddRolePermission( bo );

        return affectedRows1 > 0 || affectedRows2 > 0;
    }
}
