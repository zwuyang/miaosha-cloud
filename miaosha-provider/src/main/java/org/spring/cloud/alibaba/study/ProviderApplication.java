package org.spring.cloud.alibaba.study;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yzw
 * @Classname ProviderApplication
 * @Description TODO
 * @Date 2020/8/11 21:36
 */
@Slf4j
@MapperScan({"org.spring.cloud.alibaba.study.mapper"})
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
        log.info("STARTED OVER ");
    }
}
