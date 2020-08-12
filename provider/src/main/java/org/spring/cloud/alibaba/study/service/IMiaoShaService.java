package org.spring.cloud.alibaba.study.service;

/**
 * @author yzw
 * @Classname IMiaoShaService
 * @Description TODO
 * @Date 2020/8/12 10:30
 */
public interface IMiaoShaService {

    /**
     * 秒杀某件商品
     * @param goodsName
     * @return
     */
    boolean ms(long goodsName);
}
