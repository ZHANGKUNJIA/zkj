package com.atstudy.service.impl;

import com.atstudy.bean.bo.AddAdminBo;
import com.atstudy.bean.bo.AdminSearchBo;
import com.atstudy.bean.bo.UpdateAdminBo;
import com.atstudy.bean.po.Admin;
import com.atstudy.mapper.AdminMapper;
import com.atstudy.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//业务层实现类-负责用户业务
@Service                //代表托管给spring框架
public class AdminService implements IAdminService {

    //成员属性 （上层AdminService类，下层是AdminMapper数据访问层接口类）
    @Autowired          //代表需要由spring框架实例化
    AdminMapper adminMapper;


    @Override
    public List<Admin> getListWithRole(AdminSearchBo adminSearchBo) {
        return adminMapper.getListWithRole(adminSearchBo);
    }

    @Override
    @Transactional( rollbackFor = Exception.class )         //保证写入数据，一致性（出现异常后，会放弃数据表的写入）
    public boolean deleteAdmin(Integer admin_id,Integer[] id_list) {
        System.out.println("service层的deleteAdmin业务方法被调用了");
        //场景1：入参是admin_id，大于0的时候，符合场景1
                    //admin_id小于等于0，返回false
        //场景2：入参是id_list，id_list必须不为null 并且 length值大于0

        boolean result = false;
        int affectedRows = 0;
        int affectedRows1 = 0;

        //判断场景1是否符合
        if(admin_id == null || admin_id<=0) {
            //判断场景2，如果id_list为null 或者 id_list.length==0
            if(id_list==null || id_list.length==0)
                return result;      //参数校验不通过，而返回失败
            else {
                //场景2生效
                affectedRows = adminMapper.multiDel(id_list);
                System.out.println("批量用户数据删除，affectedRows:"+affectedRows);     //增删改都是比较敏感的操作（数据写入），操作完毕后，需要记录一下结果

                //批量删除指定的用户列表的关联角色数据->新增一个数据访问方法，能批量删除id_list的admin_role的数据
                affectedRows1 = adminMapper.multiDeleteAdminRole(id_list);
                System.out.println("批量用户的角色数据删除，affectedRows1:"+affectedRows1);
            }
        }
        else{
            //场景1生效
            affectedRows = adminMapper.deleteOne(admin_id);
            System.out.println("admin_id="+admin_id+"的数据删除，affectedRows:"+affectedRows);     //增删改都是比较敏感的操作（数据写入），操作完毕后，需要记录一下结果

            //判断一下affectedRows如果大于0，说明第一步删除用户是成功的，此时需要级联删除这个用户的关联角色信息
            if(affectedRows>0){
//                try {
                    affectedRows1 = adminMapper.deleteAdminRoleByAdminId(admin_id);
                    //有些用户可能没有设置过关联角色，所以affectedRows1返回的结果是否大于0，并不用决定整个业务方法的bool返回值
                    System.out.println("admin_id=" + admin_id + "删除关联角色数据,affectedRows1:" + affectedRows1);
//                }catch (Exception exception){
//                    //todo，模拟出现第二步调用异常的场景
//                    exception.printStackTrace();
//                    affectedRows1 = -1;
//                }
            }
        }

        if(affectedRows>0){
            //受影响行数大于0，就返回true
            result = true;
        }

        return result;
    }

    //添加员工的业务方法，从数据一致性的角度上说，有没有额外的事情，需要做的？
    //标准：什么时候需要加事务——业务方法中有多次数据库的增删改->想要保持数据的一致性->用spring/springboot事务来做
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(AddAdminBo addAdminBo) {
//        1.初始化一下初始密码的字段
        addAdminBo.setAdmin_pass("abc123");     //todo，暂不做加密+签名+加盐

//        2.调用添加员工的访问层功能
        int affectedRows = adminMapper.addOne(addAdminBo);
        int affectedRows2 = -1;

        if(affectedRows>0){
            //继续3.调用添加员工-角色的访问层功能（1个员工，必须至少有1个角色）
            affectedRows2 = adminMapper.multiAddAdminRole(addAdminBo);

            //只有受影响的行数1 ，和 受影响的行数2 都大于0，代表成功
            return affectedRows2>0;
        }
        else {
            //返回失败
            return false;
        }
    }

    @Override
    public Admin getOneWithRoleList(int admin_id) {
        return adminMapper.getOneWithRoleList(admin_id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)           //spring事务的注解
    public boolean update(UpdateAdminBo updateAdminBo) {
        boolean result = false; //默认false

        //1.调用修改员工的访问层功能。。。。
        int affectedRow1 = adminMapper.updateOne(updateAdminBo);
        int affectedRow2 = 0;
        int affectedRow3 = 0;

        //2.调用删除员工-角色的访问层功能。。。
        if(affectedRow1<=0)
        {
            return result;      //受影响的行数非法，返回失败
        }
        affectedRow2 = adminMapper.deleteAdminRoleByAdminId(updateAdminBo.getAdmin_id());
        //有可能一个用户，从未设置过角色数据，无须判断affectedRow2是否大于0

        //3.调用批量添加员工-角色的访问层功能。
        //需要AddAdminBo类型，实例化+初始化这个类型的数据
        AddAdminBo bo =new AddAdminBo();
        bo.setAdmin_id(updateAdminBo.getAdmin_id());
        bo.setRoleList(updateAdminBo.getRoleList());

        affectedRow3 = adminMapper.multiAddAdminRole(bo);

        //返回成功的条件（affectedRow1>0 ）
        return affectedRow1>0 ;
    }

    //告诉security框架，怎么得到一个  用户登录界面中，录入的登录名的Admin实体信息
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Admin admin = adminMapper.getOneWithRoleAndMenuByName( username );
        if( admin == null ){
            throw new UsernameNotFoundException("账户名称或密码错误！请重新填写！");
        }
        return admin;
    }
}
