package com.atstudy.security;

import com.atstudy.security.handler.LoginErrorHandle;
import com.atstudy.security.handler.LoginSuccessHandle;
import com.atstudy.security.handler.LogoutSuccessHandle;
import com.atstudy.security.handler.PermissionErrorHandle;
import com.atstudy.service.IAdminService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

@Configuration              //托管给spring框架
@EnableGlobalMethodSecurity(prePostEnabled = true)      //seucrity框架层面提供的注解信息，开启全局的认证校验+权限校验
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private PermissionAuthority permissionAuthority;

    @Resource
    private PermissionValid permissionValid;

    @Resource
    private IAdminService adminService;
    @Resource
    private LoginErrorHandle loginErrorHandle;

    @Resource
    private LoginSuccessHandle loginSuccessHandle;

    @Resource
    private LogoutSuccessHandle logoutSuccessHandle;

    @Resource
    private PermissionErrorHandle permissionErrorHandle;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置Security
        http.headers().frameOptions().disable() // 允许iframe嵌套
                .and()
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(permissionAuthority);
                        o.setAccessDecisionManager(permissionValid);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .loginPage("/index/login")                      //告诉security框架，需要用自己写的登录页面
                .loginProcessingUrl("/index/loginDo")           //告诉security框架，post到哪个url的请求，是登录请求
                .usernameParameter("admin_name")                //登录时，用户名字段
                .passwordParameter("admin_pass")                //登录时，密码字段
                .failureHandler( loginErrorHandle )             //登录失败的处理
                .successHandler( loginSuccessHandle )           //登录成功的处理
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/index/logout")
                .logoutSuccessHandler( logoutSuccessHandle )    //退出成功的处理
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(permissionErrorHandle);     //权限判断失败（没有权限情况下）的处理
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置登录验证方式
        auth.userDetailsService(adminService)       //业务层的成员属性中，会先加载指定admin_name的用户信息，接下来需要指定密码字段的策略
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置例外路径......“公共页面”是不需要权限校验
        web.ignoring().antMatchers("/error","/actuator/**","/login","/druid/**","/index/login","/index/error","/index/success","/common/**","/img/**","/css/**","/js/**","/favicon.ico","/cate/**","/spurest/**")
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui", "/configuration/security");
    }
}
