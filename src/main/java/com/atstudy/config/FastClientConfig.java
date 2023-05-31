package com.atstudy.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jmx.support.RegistrationPolicy;

//fastdfs的配置类，
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastClientConfig {
    @Value("fdfs.imgUrl")
    public String imgUrl ;

}