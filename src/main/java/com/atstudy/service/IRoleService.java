package com.atstudy.service;

import com.atstudy.bean.bo.AddRoleBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.RoleSearchBo;
import com.atstudy.bean.bo.UpdateRoleBo;
import com.atstudy.bean.po.Role;

import java.util.List;

//业务层接口类-负责角色业务
public interface IRoleService {

    //抽象方法
    //返回所有的角色列表的数据
    List<Role> getAll();

    //根据查询条件，查询角色列表
    List<Role> getList(RoleSearchBo roleSearchBo, PageBo pageBo);


    // 添加角色
    boolean addOne(AddRoleBo addRoleBo);


    // 删除单个角色
    boolean deleteOne( Integer role_id );

    // 批量删除多个角色
    boolean multiDelete( Integer[] id_list );


    // 根据角色id查询一个角色实体模型对象
    Role getOne( Integer role_id );


    // 修改角色
    boolean update(UpdateRoleBo updateRoleBo);


}
