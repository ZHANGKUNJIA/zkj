package com.atstudy.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration          //相当于增加了一个注解@Component，托管给spring框架，在实例化该类型的时候由上下文对象进行实例化
public class ConnConfig {
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setFilters("stat");//加入Druid监控功能
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return druidDataSource;
    }
    /**
     * 配置Druid的监控访问页————如果要访问当前项目的mysql监控信息，就需要访问/druid/
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean<StatViewServlet>(statViewServlet, "/druid/*");
        return servletRegistrationBean;
    }
}