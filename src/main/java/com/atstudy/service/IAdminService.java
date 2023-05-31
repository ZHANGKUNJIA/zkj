package com.atstudy.service;

import com.atstudy.bean.bo.AddAdminBo;
import com.atstudy.bean.bo.AdminSearchBo;
import com.atstudy.bean.bo.UpdateAdminBo;
import com.atstudy.bean.po.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

//业务层接口类-负责用户业务
public interface IAdminService  extends UserDetailsService {

    //抽象方法
    //按照4个查询条件，要查询员工列表的数据（需要带出查出的用户的角色数据）
    List<Admin> getListWithRole(AdminSearchBo adminSearchBo);

    //删除员工 的功能
    boolean deleteAdmin(
            Integer admin_id ,       //单选删除的入参
            Integer[] id_list
    );

    //添加员工功能
    boolean add(AddAdminBo addAdminBo);

    //通过admin_id得到Admin实体信息（带roleList）
    Admin getOneWithRoleList(int admin_id);

    //修改员工功能
    boolean update(UpdateAdminBo updateAdminBo);
}
