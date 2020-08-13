package org.spring.cloud.alibaba.study.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yzw
 * @Classname ConsumerConfiguration
 * @Description TODO
 * @Date 2020/8/11 22:27
 */
@Configuration
public class ConsumerConfiguration {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
