package org.spring.cloud.alibaba.study.config;

import lombok.extern.slf4j.Slf4j;
import ms.tool.redis.RedisServer;
import ms.tool.redis.key.BasePrefix;
import ms.tool.redis.key.PrefixKey;
import org.spring.cloud.alibaba.study.dto.MSGoodsDto;
import org.spring.cloud.alibaba.study.mapper.MiaoshaGoodsMapper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @author yzw
 * @Classname SystemConfig
 * @Description TODO
 * @Date 2020/8/12 15:19
 */
@Slf4j
@Component
public class SystemConfig implements InitializingBean {

    @Autowired
    private MiaoshaGoodsMapper msGoodsMapper;

    @Autowired
    private RedisServer redisService;


    @Override
    public void afterPropertiesSet() throws Exception {
        PrefixKey basePrefix =  new BasePrefix(0,"ms");
        List<MSGoodsDto> msGoodsDtoList = msGoodsMapper.getAllMiaoShaGoods();
        if (msGoodsDtoList == null){
            log.info("没有要秒杀的商品，系统初始化失败。");
            return;
        }
        log.info("秒杀系统正在初始化");
        for (MSGoodsDto msGoodsDto: msGoodsDtoList) {
            redisService.set((PrefixKey) basePrefix, "" + msGoodsDto.getId(),msGoodsDto.getStockCount());
            log.info("商品{}本轮秒杀共计{}件", msGoodsDto.getGoodsName(), msGoodsDto.getStockCount());
        }
        log.info("秒杀系统初始化完成");
    }


}
