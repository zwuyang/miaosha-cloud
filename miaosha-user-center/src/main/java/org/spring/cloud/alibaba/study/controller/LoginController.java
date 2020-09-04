package org.spring.cloud.alibaba.study.controller;

import org.spring.cloud.alibaba.study.common.result.ResultMessage;
import org.spring.cloud.alibaba.study.dto.UserRegisterDto;
import org.spring.cloud.alibaba.study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yzw
 * @Classname LoginController
 * @Description TODO
 * @Date 2020/8/14 09:45
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public ResultMessage login(@RequestBody UserRegisterDto userRegisterDto, HttpServletResponse response){
        return userService.login(userRegisterDto, response);
    }
}
