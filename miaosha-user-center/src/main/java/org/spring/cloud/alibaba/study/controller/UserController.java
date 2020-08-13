package org.spring.cloud.alibaba.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.alibaba.study.common.result.ResultMessage;
import org.spring.cloud.alibaba.study.common.result.StatusEnum;
import org.spring.cloud.alibaba.study.dto.UserRegisterDto;
import org.spring.cloud.alibaba.study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yzw
 * @Classname UserController
 * @Description TODO
 * @Date 2020/8/13 11:40
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/reg")
    public ResultMessage register(@RequestBody UserRegisterDto userRegisterDto){
        return userService.register(userRegisterDto);
    }

    @GetMapping("/register")
    public ResultMessage registerWithCookie(@RequestBody UserRegisterDto userRegisterDto, HttpServletResponse response){
        return userService.register(userRegisterDto, response);
    }

    @GetMapping("/register1")
    public ResultMessage register1(@RequestBody UserRegisterDto userRegisterDto){
        return userService.registerNoInfo(userRegisterDto);
    }
}
