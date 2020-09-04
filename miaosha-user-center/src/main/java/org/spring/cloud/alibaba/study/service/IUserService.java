package org.spring.cloud.alibaba.study.service;

import org.spring.cloud.alibaba.study.common.result.ResultMessage;
import org.spring.cloud.alibaba.study.dto.UserInfoDto;
import org.spring.cloud.alibaba.study.dto.UserRegisterDto;
import org.spring.cloud.alibaba.study.entity.MiaoshaUser;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yzw
 * @Classname IUserService
 * @Description 秒杀用户中心服务
 * @Date 2020/8/13 10:26
 */
public interface IUserService {

    ResultMessage<MiaoshaUser> register(UserRegisterDto userRegisterDto);

    ResultMessage registerNoInfo(UserRegisterDto userRegisterDto);

    ResultMessage register(UserRegisterDto userRegisterDto, HttpServletResponse response);

    List<String> getAllUsedNames();

    ResultMessage login(UserRegisterDto userRegisterDto, HttpServletResponse response);

    MiaoshaUser getMiaoShaUserById(String userId);
}
