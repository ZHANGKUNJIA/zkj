package com.atstudy.security;

import com.atstudy.bean.po.Role;
import com.atstudy.mapper.RoleMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 授权类
 * 当用户访问一个资源路径时  需要知道哪些角色，可以访问这个url
 * */
@Component                  //已经让spring框架托管了
public class PermissionAuthority implements FilterInvocationSecurityMetadataSource {

    @Resource
    private RoleMapper roleMapper;

    //返回的信息：给定的url入参，有哪些角色列表可以访问它（补漏洞）
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        // 获取请求的URL
        String requestUrl = ( (FilterInvocation) object ).getRequestUrl();
        // 判断请求的URL是否含有？（参数）
        if( requestUrl.indexOf("?") != -1 ) {
            // 将请求的URL去掉参数部分
            requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
        }
        System.out.println(" 当前需要判断授权的URL = " + requestUrl );
        //  根据URL获取可以访问角色列表
        List<Role> roleList = roleMapper.getListByOperateUrl(requestUrl);
        System.out.println( " 当前得到，当前访问URL，有哪些角色列表可以访问 = " + roleList.size() );
        // 判断角色列表是否为空
        if( !roleList.isEmpty() && roleList.size() > 0 ){
            // 准备数组存放授权列表
            String[] roles = new String[roleList.size()];
            // 循环角色列表中的每一个元素
            for( int i = 0 ; i <= roles.length - 1; i++ ){
                // 将当前循环到角色存放到授权列表中
                roles[i] = roleList.get(i).getRole_name();
                System.out.println(" 授权 => " + roleList.get(i).getRole_name() );
            }
            // 返回授权列表
            return SecurityConfig.createList(roles);
        }
        // 避免返回null（任何角色都能访问）
        System.out.println(" 当前访问的页面是公开页面，不需要权限验证！ ");
        return SecurityConfig.createList("PublicPermission");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
