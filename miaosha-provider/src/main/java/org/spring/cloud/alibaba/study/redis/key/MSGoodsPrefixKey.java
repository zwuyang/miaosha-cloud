package org.spring.cloud.alibaba.study.redis.key;

/**
 * @author yzw
 * @Classname MSGoodsPrefixKey
 * @Description TODO
 * @Date 2020/8/12 16:42
 */
public class MSGoodsPrefixKey extends BasePrefix{

    private static volatile MSGoodsPrefixKey msGoodsPrefixKey;

    private MSGoodsPrefixKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public synchronized static MSGoodsPrefixKey getInstance(){
        if (msGoodsPrefixKey == null){
            synchronized (MSGoodsPrefixKey.class){
                if (msGoodsPrefixKey == null){
                    msGoodsPrefixKey = new MSGoodsPrefixKey(0, "ms");
                }
            }
        }
        return msGoodsPrefixKey;
    }
}
