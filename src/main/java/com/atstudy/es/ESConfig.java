package com.atstudy.es;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;


//es的配置类（需要告诉spring/springboot框架），要连哪个es服务器，返回一个组件
@Configuration
public class ESConfig extends AbstractElasticsearchConfiguration {
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo("192.168.137.91:9200")
                .build();
        RestHighLevelClient client = RestClients.create(configuration).rest();
        return client;
    }
}