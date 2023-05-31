package com.atstudy.controller;

import com.atstudy.bean.bo.AddRoleBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.RoleSearchBo;
import com.atstudy.bean.bo.UpdateRoleBo;
import com.atstudy.bean.po.Permission;
import com.atstudy.bean.po.Role;
import com.atstudy.service.IPermissionService;
import com.atstudy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//视图类-负责角色业务
@Controller
@RequestMapping("/role")        // /role/**的响应，都定位到当前视图方法
public class RoleController {

    //成员属性
    @Autowired
    IRoleService roleService;   //由spring框架进行实例化

    @Autowired
    IPermissionService permissionService;

    //admin的视图方法
    @RequestMapping("/admin")
    public String admin(
            RoleSearchBo roleSearchBo,       //从/role/admin.html收集查询的条件
            PageBo  pageBo,                 //分页
            Model model                         //用于从视图方法，要传递数据给html页面
    ){
        System.out.println(pageBo);   //控制台输出一下，看收集到的分页参数内容

        //调用业务层方法，新增传入一个pageBo实例，用于分页，返回List<Role>
        List<Role> roleList = roleService.getList(roleSearchBo,pageBo);


        model.addAttribute("roleList",roleList);
        model.addAttribute("roleSearchBo",roleSearchBo);
        model.addAttribute("pageBo",pageBo);

        return null;
    }

    //add的视图方法
    @RequestMapping("/add")
    public String add(
            Model model
    ){

        //调用下层业务层的，permissionService.getAll()
        List<Permission> permissionList = permissionService.getAll();
        model.addAttribute("permissionList",permissionList);

        return null;
    }

    @RequestMapping("/save")
    public String save(AddRoleBo addRoleBo){
        // 调用 业务逻辑层 添加角色的方法
        boolean result = roleService.addOne( addRoleBo );

        if( result ){
            return "redirect:/index/success?message=Success!&detail=Role Add Success!&controller=role";
        }else{
            return "redirect:/index/error?message=Error!&detail=Role Add Error!&controller=role";
        }
    }


    @RequestMapping("/update")
    public String update( Integer role_id , Model model ){

        // 查询要修改的角色的原始数据
        model.addAttribute( "role" , roleService.getOne( role_id ) );

        // 根据要修改的角色编号 查询 可以判断角色是否拥有的权限列表
        model.addAttribute( "permissionList" , permissionService.getListWithRoleByRoleId( role_id ) );

        return null;
    }

    @RequestMapping("/alter")
    public String alter(UpdateRoleBo updateRoleBo){
        // 调用 业务逻辑层 修改角色的方法
        boolean result = roleService.update( updateRoleBo );

        if( result ){
            return "redirect:/index/success?message=Success!&detail=Role Update Success!&controller=role";
        }else{
            return "redirect:/index/error?message=Error!&detail=Role Update Error!&controller=role";
        }
    }


    @RequestMapping("/delete")
    public String delete(
            Integer role_id,    // 删除一个角色
            Integer[] id_list   // 批量删除多个角色
    ){
        boolean result = false;
        // 判断 删除单个角色 还是 批量删除多个角色
        if( role_id != null ){
            // 删除单个角色
            result = roleService.deleteOne( role_id );
        }else{
            // 批量删除多个角色
            result = roleService.multiDelete( id_list );
        }

        if( result ){
            return "redirect:/index/success?message=Success!&detail=Role Delete Success!&controller=role";
        }else{
            return "redirect:/index/error?message=Error!&detail=Role Delete Error!&controller=role";
        }
    }


}
