package org.spring.cloud.alibaba.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzw
 * @Classname HelloController
 * @Description TODO
 * @Date 2020/8/18 14:15
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
