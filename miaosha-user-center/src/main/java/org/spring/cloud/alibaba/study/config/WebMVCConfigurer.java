package org.spring.cloud.alibaba.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yzw
 * @Classname WebMVCInterceptor
 * @Description TODO
 * @Date 2020/8/14 09:50
 */
@Configuration
public class WebMVCConfigurer implements WebMvcConfigurer {

    @Autowired
    private WebInterceptor webInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor)
                .excludePathPatterns("/login")
                .excludePathPatterns("/register");
    }
}
