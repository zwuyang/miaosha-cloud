package org.spring.cloud.alibaba.study;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yzw
 * @Classname UserApplication
 * @Description TODO
 * @Date 2020/8/13 10:06
 */
@Slf4j
@MapperScan({"org.spring.cloud.alibaba.study.mapper"})
@SpringBootApplication
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
        log.info("USERCENTER STARTED SUCCEED");
    }
}
