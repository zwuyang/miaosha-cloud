package org.spring.cloud.alibaba.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yzw
 * @Classname JedisConfiguration
 * @Description TODO
 * @Date 2020/8/12 16:31
 */
@Component
public class JedisConfiguration {

    @Autowired
    private RedisConfiguration redisConfiguration;

    @Bean
    public JedisPool jedisPool(){
        String host = redisConfiguration.getHost();
        String password = redisConfiguration.getPassword();
        int expire = redisConfiguration.getExpire();
        int port = redisConfiguration.getPort();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(redisConfiguration.getMaxWaitTime());
        config.setMaxIdle(redisConfiguration.getMaxIdle());
        config.setMinIdle(redisConfiguration.getMinIdle());
        config.setMaxTotal(redisConfiguration.getMaxActive());
        JedisPool jedisPool = new JedisPool(config, host, port, expire, password);
        return jedisPool;
    }

}
