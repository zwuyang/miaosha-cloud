package org.spring.cloud.alibaba.study.fallback;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.alibaba.study.service.MSService;
import org.springframework.stereotype.Component;

/**
 * @author yzw
 * @Classname MSServiceFallBack
 * @Description TODO
 * @Date 2020/8/12 10:46
 */
@Slf4j
@Component
public class MSServiceFallBack implements MSService {

    @Override
    public boolean ms(long id) {
        log.error("网络故障 秒杀失败");
        return false;
    }
}
