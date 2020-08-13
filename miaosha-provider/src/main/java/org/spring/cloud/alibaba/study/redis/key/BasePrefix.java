package org.spring.cloud.alibaba.study.redis.key;

/**
 * @author yzw
 * @Classname BasePrefix
 * @Description TODO
 * @Date 2020/8/12 14:53
 */
public class BasePrefix implements KeyPrefix{

    private int expireSeconds;

    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        this(0,prefix);
    }


    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String prefix() {
        return prefix;
    }
}
