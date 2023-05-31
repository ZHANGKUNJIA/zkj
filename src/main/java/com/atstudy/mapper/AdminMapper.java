package com.atstudy.mapper;

import com.atstudy.bean.bo.AddAdminBo;
import com.atstudy.bean.bo.AdminSearchBo;
import com.atstudy.bean.bo.UpdateAdminBo;
import com.atstudy.bean.po.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//数据访问层接口类-负责员工的业务
@Repository                     //托管给spring框架
public interface AdminMapper {

    //抽象方法
    //按照4个查询条件，要查询员工列表的数据（需要带出查出的用户的角色数据）
    List<Admin> getListWithRole(
            @Param("adminSearchBo")
            AdminSearchBo adminSearchBo);

    //单选删除、返回受影响的行数
    int deleteOne(
            @Param("admin_id")
            Integer admin_id
    );

    //批量删除
    int multiDel(
            @Param("id_list")
            Integer[] id_list
    );

    //级联删除1个用户的关联角色数据
    int deleteAdminRoleByAdminId(
            @Param("admin_id") Integer admin_id);

    //级联删除 多个用户的关联角色数据
    int multiDeleteAdminRole(
            @Param("id_list")
            Integer[] id_list
    );

    //添加员工
    int addOne(
            @Param("addAdminBo")
            AddAdminBo addAdminBo);

    //批量添加员工-角色表的数据
    int multiAddAdminRole(
            @Param("addAdminBo")
            AddAdminBo addAdminBo);

    //返回一个带roleList的Admin信息
    Admin getOneWithRoleList(
            @Param("admin_id")
            int admin_id
    );

    //修改一个admin主表的信息
    int updateOne(
            @Param("updateAdminBo")
            UpdateAdminBo updateAdminBo);

    //通过登录名，查找这个登录名的 Admin实体信息
    Admin getOneByName(
            @Param("admin_name")
            String admin_name);

    //通过登录名，查找这个登录名的Admin实体信息（带 角色列表/菜单列表）
    Admin getOneWithRoleAndMenuByName(
            @Param("admin_name")
            String admin_name);
}
