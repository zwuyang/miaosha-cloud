package org.spring.cloud.alibaba.study.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author yzw
 * @Classname RedissionConfiguration
 * @Description TODO
 * @Date 2020/8/12 16:30
 */
@Component
public class RedissionConfiguration {

    @Autowired
    private RedisConfiguration redisConfiguration;

    @Bean
    public RedissonClient redissonClient(){
        String host = redisConfiguration.getHost();
        String pass = redisConfiguration.getPassword();
        int port = redisConfiguration.getPort();
        String address = "redis://" + host + ":" + port;
        Config config = new Config();
        config.useSingleServer().setAddress(address);
        config.useSingleServer().setPassword(pass);
        return Redisson.create(config);
    }
}
