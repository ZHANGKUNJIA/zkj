package com.atstudy.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限验证类 判断当前登录的账户的角色列表中 是否含有 当前请求资源的授权角色
 * */
@Component
public class PermissionValid implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        // 迭代遍历当前请求的URL授权的所有角色（从PermissionAuthority得到的角色列表信息）
        for( ConfigAttribute attribute : configAttributes ){

            // 判断当前账户是否为空
            if (authentication == null) {
                throw new AccessDeniedException("权限认证失败！");
            }

            // 获取当前请求的授权角色
            String needRole = attribute.getAttribute();

            // 判断是否是公开权限
            if ("PublicPermission".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录");
                } else
                    return;
            }
            // 获取当前账户所拥有的角色列表
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // 迭代遍历当前账户的每一个角色
            for (GrantedAuthority authority : authorities) {
                System.out.println( " 权限验证 => 授权（"+needRole+"），验证（" + authority.getAuthority() + "）" );
                // 判断当前账户的角色是否和当前循环到的授权角色相同
                if (authority.getAuthority().equals(needRole)) {
                    System.out.println( " 权限验证通过！ " );
                    return;
                }
            }
        }
        // 认证失败
        System.out.println( " 权限验证失败！ " );
        throw new AccessDeniedException("权限认证失败！");

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
