package com.atstudy.controller;

import com.atstudy.bean.po.Admin;
import com.atstudy.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//视图类-负责/index/**的访问，需要定位到当前视图类
@RequestMapping("/index")
@Controller                     //托管给spring框架
public class IndexController {
    //成员属性
    @Autowired
    RedisUtils redisUtils;          //indexcontroller是上层，要操作redis需要通过帮助类RedisUtils来进行，所以RedisUtils类型是下层



    //视图方法

    // /index/userindex
    @RequestMapping("/userindex")
    public String userindex(
            Model model             //需要往这个model中增加一个变量visitcount，记录被访问次数
    ){
        int visitcount = 0;
        String key="visitcount";

        //第一个逻辑：每次访问到视图方法的时候，就要对于visitcount的值要记录，自增一下
        //redisutils操作帮助类。。。。
        //使用这个帮助类中的哪个方法？取值get
        if(redisUtils.get(key) !=null) {
            //非首次访问
            visitcount = Integer.parseInt(redisUtils.get(key).toString());     //redis取值+int类型转换
        }
        else {
            //第一次访问
            visitcount =0;
        }

        //自增
        visitcount++;

        //设置值set
        redisUtils.set(key,visitcount);

        //第二个逻辑，/index/userindex的页面中，显示一下当前页面总计被访问了多少次
        model.addAttribute(key,visitcount);

        return null;                //调度 /resouorces/templates/index/userindex.html视图文件响应当前的访问
    }

    //视图方法，响应 /index/error，错误提示页面
    //  /index/error?message=Error!&detail=Admin Delete Error!&controller=admin
    @RequestMapping("/error")
    public String error(
            String message,                         //信息概述
            String detail,                          //详细错误提示的信息
            String controller,                       //返回哪个模块的信息
            Model   model                           //需要让java视图方法，传递参数给thymeleaf页面
    ){
        model.addAttribute("message",message);
        model.addAttribute("detail",detail);
        model.addAttribute("controller",controller);
        return null;
    }

    //视图方法， 响应/index/success，正确提示信息页面
    //  /index/success?message=Success!&detail=Admin Delete Success!&controller=admin
    @RequestMapping("/success")
    public  String success(
            String message,                         //信息概述
            String detail,                          //详细错误提示的信息
            String controller,                       //返回哪个模块的信息
            Model   model                           //需要让java视图方法，传递参数给thymeleaf页面
    ){
        model.addAttribute("message",message);
        model.addAttribute("detail",detail);
        model.addAttribute("controller",controller);
        return null;
    }

    //设置登录界面的视图方法,/index/login
    @RequestMapping("/login")
    public String login(){
        return null;
    }

    //设置登录成功后的视图方法
    @RequestMapping("/index")
    public String index(
            Authentication auth,  //security框架中提供的一个接口，框架已经帮你自动实现（当前登录成功的账号Admin实体，存到这个接口中）
            Model model                     //入参中需要包含model，用于theymleaf页面数据显示
    )
    {
        // 获取当前登录的员工对象
        Admin admin = null;
        if(auth!=null) {
            admin = (Admin) auth.getPrincipal();
        }
        else {
            admin = new Admin();
        }

        model.addAttribute("admin",admin);

        return null;
    }

    //设置登录成功/index/index中的iframe的视图方法
    @RequestMapping("/home")
    public String home(){
        return null;
    }
}
