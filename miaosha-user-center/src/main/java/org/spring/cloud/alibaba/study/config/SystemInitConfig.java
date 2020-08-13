package org.spring.cloud.alibaba.study.config;

import lombok.extern.slf4j.Slf4j;

import org.spring.cloud.alibaba.study.entity.MiaoshaUser;
import org.spring.cloud.alibaba.study.mapper.MiaoshaUserMapper;
import org.spring.cloud.alibaba.study.redis.RedisService;
import org.spring.cloud.alibaba.study.redis.key.UserKeyPrefix;
import org.spring.cloud.alibaba.study.service.IUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author yzw
 * @Classname SystemConfig
 * @Description TODO
 * @Date 2020/8/12 15:19
 */
@Slf4j
@Component
public class SystemInitConfig implements InitializingBean {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisService redisService;


    @Override
    public void afterPropertiesSet() throws Exception {
        initUsedName();
    }

    /**
     * 将注册的用户名放到redis
     * 没什么意义。
     */
    private void initUsedName() {
        Set<String> list = redisService.getSets(UserKeyPrefix.getInstance(), "used:name");
        if (!CollectionUtils.isEmpty(list)){
            log.info("已经初始化过了，不需要再次初始化");
            return;
        }
        List<String> allUsedNames = userService.getAllUsedNames();
        if (CollectionUtils.isEmpty(allUsedNames)){
            log.info("暂时还没有用户注册，不需要初始化已被注册的用户名");
            return;
        }else{
            String[] values = allUsedNames.toArray(new String[]{});
            redisService.addSet(UserKeyPrefix.getInstance(), "used:name", values);
        }
        log.info("注册的用户名已导入缓存。系统当前已注册用户数为{}", allUsedNames.size());
    }


}
