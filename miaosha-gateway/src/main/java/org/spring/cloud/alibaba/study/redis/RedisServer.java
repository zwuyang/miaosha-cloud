package org.spring.cloud.alibaba.study.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @author yzw
 * @Classname RedisServer
 * @Description TODO
 * @Date 2020/8/18 10:29
 */
@Service
public class RedisServer {

    @Autowired
    private JedisPool jedisPool;

    public boolean putAll(String key, Map<String, String> map){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long res = jedis.hset(key, map);
            return res > 0;
        }finally {
            jedis.close();
        }
    }

    public boolean set(String key, String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String res = jedis.set(key, value);
            return res.equals("OK");
        }finally {
            jedis.close();
        }
    }

    public String get(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }finally {
            jedis.close();
        }
    }
}
