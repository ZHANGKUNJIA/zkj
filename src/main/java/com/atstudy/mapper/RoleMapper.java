package com.atstudy.mapper;

import com.atstudy.bean.bo.AddRoleBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.RoleSearchBo;
import com.atstudy.bean.bo.UpdateRoleBo;
import com.atstudy.bean.po.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//数据层接口类-负责角色
@Repository             //代表将当前java类要托管给spring框架
public interface RoleMapper {

    //抽象成员方法
    //从数据库中得到所有的角色列表
    List<Role> getAll();

    //通过admin_id得到这个用户，所有的角色列表数据
    List<Role> getListByAdminId(
            @Param("admin_id")
            int admin_id);

    //按照查询条件进行，角色列表的查询
    //步骤二，新增一个入参。。。用于分页的条件
    List<Role> getList(
            @Param("roleSearchBo")
            RoleSearchBo roleSearchBo,
            @Param("pageBo")
            PageBo pageBo
    );

    //步骤一，新增getCount(RoleSearchBo)
    int getCount(
            @Param("roleSearchBo")
            RoleSearchBo roleSearchBo
    );


    // 添加角色
    int addOne(@Param("addRoleBo") AddRoleBo addRoleBo);


    // 批量添加角色权限关系数据
    int multiAddRolePermission( @Param("addRoleBo") AddRoleBo addRoleBo );

    // 修改角色
    int putOne(@Param("updateRoleBo") UpdateRoleBo updateRoleBo);

    // 根据角色编号删除所有该角色的权限关系
    int deleteRolePermissionByRoleId(@Param("role_id") Integer role_id );

    // 删除一个角色
    int deleteOne(@Param("role_id") Integer role_id );

    // 批量删除多个角色
    int multiDelete( @Param("id_list") Integer[] id_list );

    // 批量删除多个角色的权限关系
    int multiDeleteRolePermission( @Param("id_list") Integer[] id_list );


    // 根据角色编号查询一个角色实体模型对象
    Role getOne( @Param("roleId") Integer roleId );


    // 根据 访问的资源路径 获取 可以访问的角色列表
    List<Role> getListByOperateUrl(@Param("operate_url") String operate_url );
}
