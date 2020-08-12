package org.spring.cloud.alibaba.study.service;

import org.spring.cloud.alibaba.study.fallback.MSServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yzw
 * @Classname MSService
 * @Description TODO
 * @Date 2020/8/12 10:46
 */
@FeignClient(value = "service-provider", fallback = MSServiceFallBack.class)
public interface MSService {

    @GetMapping("/ms/{id}")
    boolean ms(@PathVariable long id);

}
