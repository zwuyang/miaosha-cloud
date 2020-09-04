package org.spring.cloud.alibaba.study.dto;

import lombok.Data;

/**
 * @author yzw
 * @Classname UserRedisterDto
 * @Description 用户注册时对象
 * @Date 2020/8/13 10:28
 */
@Data
public class UserRegisterDto {

    private String nickName;

    private String password;


}
