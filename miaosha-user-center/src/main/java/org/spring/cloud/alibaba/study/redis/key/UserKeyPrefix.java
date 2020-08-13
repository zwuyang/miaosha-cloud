package org.spring.cloud.alibaba.study.redis.key;

import org.spring.cloud.alibaba.study.entity.User;

/**
 * @author yzw
 * @Classname UserKeyPrefix
 * @Description TODO
 * @Date 2020/8/13 15:54
 */
public class UserKeyPrefix extends BasePrefix{


    public UserKeyPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public UserKeyPrefix(String prefix) {
        super(prefix);
    }

    static enum  UserKeyPrefixEnum{
        INSTANCE;

        private UserKeyPrefix userKeyPrefix;

        private UserKeyPrefixEnum(){
            userKeyPrefix = new UserKeyPrefix(0,"user:center");
        }

        public UserKeyPrefix getInstance(){
            return userKeyPrefix;
        }
    }

    public static UserKeyPrefix getInstance(){
        return UserKeyPrefixEnum.INSTANCE.getInstance();
    }



}
