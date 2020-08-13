package org.spring.cloud.alibaba.study.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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





}
