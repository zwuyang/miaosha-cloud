package org.spring.cloud.alibaba.study.config;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.alibaba.study.common.util.IPUtil;
import org.spring.cloud.alibaba.study.entity.MiaoshaUser;
import org.spring.cloud.alibaba.study.mapper.MiaoshaUserMapper;
import org.spring.cloud.alibaba.study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author yzw
 * @Classname WebInterceptor
 * @Description TODO
 * @Date 2020/8/14 09:52
 */
@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IPUtil.getIPAddr(request);
        boolean isInterIp = IPUtil.interIP(ip);
        String url = request.getRequestURL().toString();
        log.info("当前访问:{} IP:{} 是否是内网IP：{}",url, ip ,isInterIp );
        String token;
        boolean valid = false;
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            log.error("COOKIE IS NULL 重新登录吧" );
            forwardToLogin(request, response);
            return valid;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                token = cookie.getValue();
                if (validToken(token)){
                    valid = true;
                }
            }
        }
        if (valid == false){
            log.error("Token 验证失败了 重新登录吧" );
            forwardToLogin(request, response);
        }
        return valid;
    }

    private void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login");
        requestDispatcher.forward(request, response);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean validToken(String tokenStr){
        String token = tokenStr.substring("user_token_".length());
        //这里token可以放到缓存中，校验逻辑后续更改，token内容也应该变化。
        MiaoshaUser user = userService.getMiaoShaUserById(token);
        return user != null;
    }
}
