package org.spring.cloud.alibaba.study.config;

import lombok.Data;

import ms.tool.redis.RedisServer;
import ms.tool.redis.config.PoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author yzw
 * @Classname RedisConfiguration
 * @Description TODO
 * @Date 2020/8/12 14:29
 */
@Data
@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.expire}")
    private int expire;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitTime;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Bean
    public RedisServer redisServer(){
        PoolConfig config = new PoolConfig(host, port, password, expire,
                            maxWaitTime, maxActive, maxIdle, minIdle);
        RedisServer redisServeR = new RedisServer(config);
        return redisServeR;
    }


}
