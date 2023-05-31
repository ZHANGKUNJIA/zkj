package com.atstudy.mapper;

import com.atstudy.bean.po.Permission;
import com.atstudy.bean.vo.PermissionWithRoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//权限功能数据访问层
@Repository
public interface PermissionMapper {
    //得到所有的权限功能列表
    List<Permission> getAll();


    // 根据角色编号 查询 可以判断角色是否拥有的权限列表
    List<PermissionWithRoleVo> getListWithRoleByRoleId(@Param("role_id") Integer role_id );
}
