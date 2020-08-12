package org.spring.cloud.alibaba.study.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.spring.cloud.alibaba.study.redis.key.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yzw
 * @Classname RedisService
 * @Description TODO
 * @Date 2020/8/12 14:43
 */
@Slf4j
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    private static final String SUCCESS = "OK";

    public <T> boolean set(KeyPrefix keyPrefix, String key, T value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String val = beanToString(value);
            if (StringUtils.isEmpty(val)) {
                return false;
            }
            String res;
            String realKey = createRealKey(keyPrefix, key);
            int seconds = keyPrefix.expireSeconds();
            if (seconds <= 0) {
                res = jedis.set(realKey, val);
            } else {
                res = jedis.setex(realKey, seconds, val);
            }
            return StringUtils.equals(res, SUCCESS);
        } finally {
            closeResource(jedis);
        }
    }

    public <T> T get(KeyPrefix keyPrefix, String key, Class<?> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey =  createRealKey(keyPrefix, key);
            String beanStr = jedis.get(realKey);
            T t = stringToBean(beanStr,clazz);
            return t;
        }finally {
            closeResource(jedis);
        }
    }

    /**
     * 减少值
     * */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = createRealKey(prefix, key);
            return  jedis.decr(realKey);
        }finally {
            closeResource(jedis);
        }
    }



    private <T> T stringToBean(String beanStr, Class<?> clazz) {
        if (StringUtils.isEmpty(beanStr)){
            return null;
        }
        if (clazz == int.class || clazz == Integer.class){
            return (T) Integer.valueOf(beanStr);
        }else if (clazz == long.class || clazz == Long.class){
            return (T) Long.valueOf(beanStr);
        }else if (clazz == String.class){
            return (T) beanStr;
        }else {
            return (T) JSON.toJavaObject(JSON.parseObject(beanStr), clazz);
        }
    }

    private String createRealKey(KeyPrefix keyPrefix, String key) {
        return keyPrefix.prefix() + ":" + key;
    }


    private <T> String beanToString(T value) {
        if (value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class){
            return value + "";
        }else if (clazz == String.class){
            return (String) value;
        }else if (clazz == long.class || clazz == Long.class){
            return value + "";
        }else {
            return JSON.toJSONString(value);
        }
    }

    private void closeResource(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }
}
