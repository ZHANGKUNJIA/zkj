package com.atstudy.controller;

import com.atstudy.bean.bo.AddAdminBo;
import com.atstudy.bean.bo.AdminSearchBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.UpdateAdminBo;
import com.atstudy.bean.po.Admin;
import com.atstudy.bean.po.Role;
import com.atstudy.service.IAdminService;
import com.atstudy.service.IRoleService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//视图类-负责员工 ,响应所有/admin/**的用户请求
@RequestMapping("/admin")
@Controller                 //托管给spring框架
public class AdminController {

    //成员属性
    @Autowired              //需要由spring框架实例化
    IRoleService  roleService;

    @Autowired              //需要有spring框架实例化
    IAdminService adminService;

    //视图方法,响应/admin/admin的请求
    @RequestMapping("/admin")
    public String admin(
            AdminSearchBo adminSearchBo,        //需要用参数对象化填充的方式，从/admin/admin.html的表单中采集查询条件
            Model model
    ){

        //调用业务层的getAll方法，返回List<Role>
        List<Role> roleList = roleService.getAll();
        model.addAttribute("roleList",roleList);

        //测试一下采集到的pageBo查询条件是什么？


        //调用业务层getListWithRole方法，返回List<Admin>
        List<Admin> adminList = adminService.getListWithRole(
                adminSearchBo
        );
        //if(adminList!=null && adminList.size()>0) {
        //    System.out.println("查询到第1个admin的角色记录数：" + adminList.get(0).getRoleList().size());
        //}
        model.addAttribute("adminList",adminList);

        //通过model作为中介，进行java代码->html代码的数据传递adminSearchBo
        model.addAttribute("adminSearchBo",adminSearchBo);

        return null;                //需要调度/admin/admin.html页面响应
    }

    //单选删除1个用户
    @RequestMapping("/delete")       //响应/admin/delete的请求，代表删除用户
                                    // /admin/delete?admin_id=数字。。。代表删除指定的用户
    public String delete(
               //在查询字符串中，根据admin_id的参数接收数据
               Integer admin_id,            //根据1个员工编号，要删除1个员工
               Integer[] id_list            //多选删除
    ){
        /*if(id_list!=null) {
            for (Integer value:id_list
            ) {
                System.out.println("id_list-value:"+value);
            }
        }

        System.out.println(admin_id);*/

        boolean result=false;       //操作成功或失败的结果

        //，操作业务方法，返回result结果
        result = adminService.deleteAdmin(admin_id,id_list);

        if(result){
            return "redirect:/index/success?message=Success!&detail=Admin Delete Success!&controller=admin";
        }
        else {
            return "redirect:/index/error?message=Error!&detail=Admin Delete Error!&controller=admin";
        }

    }

    //添加员工的视图页面
    @RequestMapping("/add")
    public String add(
        Model model
    ){
        //需要从视图层调用，所有角色列表的数据，绑定到页面上面->借助于辅助的变量Model model来传递数据
        List<Role> roleList = roleService.getAll();

        model.addAttribute("roleList",roleList);

        return null;        //需要调度/admin/add的html页面，响应这个视图方法
    }

    //执行添加员工的视图方法
    @RequestMapping("/save")
    public String save(
            AddAdminBo addAdminBo           //从上一个页面中，要进行取值
    ){
        /**
         * java后台的视图方法save()，入参中最复杂成员属性  roleList...类型是 List<Role>
         * ——需要从表单中，收集到select标签的一些信息，在java代码中最终会生成若干个Role的实例
         *
         * eg:从页面表单中要新增1个用户，这个用户有两个角色（role_id=3，role_id=4）
         * ——视图方法save()接收到了addAdminBo的实例，roleList会是什么样的呢？
         * roleList的第一个元素，需要是Role的实例，role_id=3........roleList[0].role_id = 3
         * roleList的第二个元素，需要也是Role的实例，role_id=4.......roleList[1].role_id = 4
         *
         * 规定：html中提交过来的roleList数据，需要按照131行+132行的格式提交过来（视图页面add.html->save()方法之间的约定）
         *
         * add.html页面中的js -refresh（）方法，就是完成上述这个规定
         */
        //System.out.println("admin_name => "+ addAdminBo.getAdmin_name());
        //System.out.println("admin_nickname => "+ addAdminBo.getAdmin_nickname());
        //if(addAdminBo.getRoleList()!=null && addAdminBo.getRoleList().size()>0){
        //    System.out.println("roleList => "+ addAdminBo.getRoleList());//用遍历的形式进行显示
        //}

        boolean result= false;  //执行添加员工的业务返回结果，默认false

        result =  adminService.add(addAdminBo);

        if(result){
            //返回统一success的页面
            return "redirect:/index/success?message=Success!&detail=Admin Add Success!&controller=admin";
        }
        else {
            //返回统一error的页面
            return "redirect:/index/error?message=Error!&detail=Admin Add Error!&controller=admin";
        }
    }

    //修改员工的界面，/admin/update?admin_id=2
    @RequestMapping("/update")
    public String update(
            int admin_id,
            Model model
    ){
        //需要从视图层调用，所有角色列表的数据，绑定到页面上面->借助于辅助的变量Model model来传递数据
        List<Role> roleList = roleService.getAll();
        model.addAttribute("roleList",roleList);

        //需要从service层调用，得到当前用户的Admin信息
        Admin admin = adminService.getOneWithRoleList(admin_id);
        model.addAttribute("admin",admin);

        return null;        //需要调度/admin/update的html页面，响应这个视图方法
    }

    //执行修改员工的功能-视图方法
    @RequestMapping("/alter")
    public String alter(
            UpdateAdminBo updateAdminBo           //从上一个页面中，要进行取值
    ){

        //System.out.println("admin_id => "+ updateAdminBo.getAdmin_id());
        //System.out.println("admin_name => "+ updateAdminBo.getAdmin_name());
        //System.out.println("admin_nickname => "+ updateAdminBo.getAdmin_nickname());
        //if(updateAdminBo.getRoleList()!=null && updateAdminBo.getRoleList().size()>0){
        //    System.out.println("roleList => "+ updateAdminBo.getRoleList());//用遍历的形式进行显示
        //}

        boolean result= false;  //执行修改员工的业务返回结果，默认false

        result =  adminService.update(updateAdminBo);

        if(result){
            //返回统一success的页面
            return "redirect:/index/success?message=Success!&detail=Admin Update Success!&controller=admin";
        }
        else {
            //返回统一error的页面
            return "redirect:/index/error?message=Error!&detail=Admin Update Error!&controller=admin";
        }

    }
}
