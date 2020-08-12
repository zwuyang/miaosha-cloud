package org.spring.cloud.alibaba.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.alibaba.study.service.MSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzw
 * @Classname MiaoShaController
 * @Description TODO
 * @Date 2020/8/12 10:45
 */
@Slf4j
@RestController
public class MiaoShaController {

    @Autowired
    private MSService msService;

    @GetMapping("/ms/{id}")
    public void ms(@PathVariable long id){
        boolean msResult = msService.ms(id);
        if (msResult){
            log.info("恭喜您 秒杀成功了。。");
        }else {
            log.info("很抱歉 该轮秒杀活动您没有中奖。");
        }
    }
}
