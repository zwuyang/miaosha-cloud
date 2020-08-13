package org.spring.cloud.alibaba.study.redis.key;

/**
 * @author yzw
 * @Classname KeyPrefix
 * @Description TODO
 * @Date 2020/8/12 14:52
 */
public interface KeyPrefix {

    int expireSeconds();

    String prefix();
}
