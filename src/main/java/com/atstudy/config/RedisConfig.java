package com.atstudy.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.io.Serializable;
import java.net.UnknownHostException;

@Configuration
public class RedisConfig {//创建一个redis的配置类；使用reids时必须要配置的一个类（没有这个类的话，java代码不能操作redis组件）

    @Bean
    @ConditionalOnMissingBean(
            name = { "redisTemplate" }
    )
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String,Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer( new StringRedisSerializer());
        redisTemplate.setValueSerializer( new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

}