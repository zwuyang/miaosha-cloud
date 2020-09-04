package org.spring.cloud.alibaba.study.service.impl;

import lombok.extern.slf4j.Slf4j;

import ms.tool.redis.RedisServer;
import ms.tool.redis.key.BasePrefix;
import ms.tool.redis.key.PrefixKey;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import org.spring.cloud.alibaba.study.mapper.MiaoshaGoodsMapper;

import org.spring.cloud.alibaba.study.service.IMiaoShaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yzw
 * @Classname MiaoShaService
 * @Description TODO
 * @Date 2020/8/12 10:31
 */
@Slf4j
@Service
public class MiaoShaService implements IMiaoShaService {

    @Autowired
    private RedisServer redisService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private MiaoshaGoodsMapper miaoshaGoodsMapper;

    private final PrefixKey msGoodsPrefixKey = new BasePrefix(0, "ms");

    private static final String MS_LOCK = "ms:lock";


    /**
     * 使用redis分布式锁去判断。
     * @param goodsId
     * @return
     */
    @Override
    public boolean ms(long goodsId) {
        RLock lock = redissonClient.getLock(MS_LOCK);
        try {
            if (lock.isLocked()) {
//                if (lock.tryLock(1,1,TimeUnit.SECONDS)){
//                    log.info("我居然抢到了锁 我是真的🐂🍺");
//                    return doMiaoSha(goodsId);
//                }
                log.info("当前参与活动人数太多 请刷新重试");
                return false;
            } else {
                lock.lock(1, TimeUnit.SECONDS);
//                return doMiaoSha(goodsId);
                boolean res = doMiaoSha(goodsId);
                lock.unlock();
                return res;
            }
        } catch (Exception e){
            log.info("获取分布式锁出现异常,本次秒杀失败");
            return false;
        }
//        finally {
//            if (lock.isLocked()){
//                lock.unlock();
//            }
//        }
    }

    /**
     * 简单判断库存
     * @param goodsId
     * @return
     */
    private boolean doMiaoSha(long goodsId) {
        long count = redisService.get(msGoodsPrefixKey, "" + goodsId, long.class);
        boolean res = false;
        if (count <= 0) {
            log.info("太惨了 商品被抢光了, 你没有秒杀到 年轻人手速不行啊 😄");
            return res;
        } else {
            log.info("秒杀仍在进行 商品还有{}件", count);
            redisService.set(msGoodsPrefixKey, "" + goodsId, --count);
            return true;
        }
    }
}
