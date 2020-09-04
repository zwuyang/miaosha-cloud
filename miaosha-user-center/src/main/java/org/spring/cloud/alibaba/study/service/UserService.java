package org.spring.cloud.alibaba.study.service;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.alibaba.study.common.result.ResultMessage;
import org.spring.cloud.alibaba.study.common.result.StatusEnum;
import org.spring.cloud.alibaba.study.common.util.MD5Util;
import org.spring.cloud.alibaba.study.dto.UserInfoDto;
import org.spring.cloud.alibaba.study.dto.UserRegisterDto;
import org.spring.cloud.alibaba.study.entity.MiaoshaUser;
import org.spring.cloud.alibaba.study.mapper.MiaoshaUserMapper;
import org.spring.cloud.alibaba.study.redis.RedisService;
import org.spring.cloud.alibaba.study.redis.key.UserKeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yzw
 * @Classname UserService
 * @Description TODO
 * @Date 2020/8/13 11:07
 */
@Slf4j
@Service
public class UserService implements IUserService{

    @Autowired
    private RedisService redisService;

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    @Override
    public ResultMessage<MiaoshaUser> register(UserRegisterDto userRegisterDto) {
        ResultMessage<MiaoshaUser> msg = ResultMessage.getInstance();
        MiaoshaUser user = doRegister(userRegisterDto);
        if (user == null){
            msg.setStatusEnum(StatusEnum.REGISTER_FAIL);
        }else {
            msg.setStatusEnum(StatusEnum.REGISTER_SUCCESS);
        }
        msg.setData(user);
        return msg;
    }

    @Override
    public ResultMessage register(UserRegisterDto userRegisterDto, HttpServletResponse response) {
        ResultMessage<MiaoshaUser> msg = ResultMessage.getInstance();
        MiaoshaUser user = doRegister(userRegisterDto);
        addCookie(user, response);
        return msg;
    }

    private boolean checkNickName(String nickName) {
        if (StringUtils.isEmpty(nickName)){
            return false;
        }
        boolean exist = redisService.existInSet(UserKeyPrefix.getInstance(), "used:name", nickName);
        if (exist){
            log.info("当前用户名已被注册啦，请填写其他的。");
            return true;
        }
        return false;
    }

    @Override
    public ResultMessage registerNoInfo(UserRegisterDto userRegisterDto) {
        ResultMessage<MiaoshaUser> msg = ResultMessage.getInstance();
        MiaoshaUser user = doRegister(userRegisterDto);
        if (user == null){
            msg.setStatusEnum(StatusEnum.REGISTER_FAIL);
        }else {
            msg.setStatusEnum(StatusEnum.REGISTER_SUCCESS);
        }
        return msg;
    }

    @Override
    public List<String> getAllUsedNames() {
        return miaoshaUserMapper.getUsedUserNames();
    }


    @Override
    public ResultMessage login(UserRegisterDto userRegisterDto, HttpServletResponse response) {
        if (StringUtils.isEmpty(userRegisterDto.getNickName())){
            return ResultMessage.ERROR("用户名为空");
        }
        String password = userRegisterDto.getPassword();
        String nickname = userRegisterDto.getNickName();
        MiaoshaUser miaoshaUser = miaoshaUserMapper.getByNickName(nickname);
        if (miaoshaUser == null){
            return ResultMessage.ERROR("登录失败 用户名或密码错误");
        }
        String dbPass = miaoshaUser.getPassword();
        String salt = miaoshaUser.getSalt();
        if (MD5Util.encodeDBPass(password, salt).equals(dbPass)){
            addCookie(miaoshaUser, response);
            return ResultMessage.getInstance();
        }
        return ResultMessage.ERROR("登录失败 用户名或密码错误");
    }

    @Override
    public MiaoshaUser getMiaoShaUserById(String userId) {
        return miaoshaUserMapper.selectByPrimaryKey(userId);
    }

    private void addCookie(MiaoshaUser user, HttpServletResponse response) {
        if (user == null){
            return;
        }
        String token = createToKenWithUserInfo(user);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
    }

    /**
     * 暂时只是放了用户id
     * @param user
     * @return
     */
    private String createToKenWithUserInfo(MiaoshaUser user) {
        String token = "user_token_" + user.getId();
        return token;
    }


    private MiaoshaUser doRegister(UserRegisterDto userRegisterDto) {
        boolean checkNickName = checkNickName(userRegisterDto.getNickName());
        if (checkNickName == true){
            return null;
        }
        UserInfoDto userInfoDto = new UserInfoDto(userRegisterDto);
        MiaoshaUser miaoshaUser = new MiaoshaUser(userInfoDto);
        try {
            int i = miaoshaUserMapper.insert(miaoshaUser);
            log.error(String.valueOf(i));
            MiaoshaUser user = miaoshaUserMapper.getByNickName(userInfoDto.getNickName());
            if (user == null){
                return null;
            }
            redisService.addSet(UserKeyPrefix.getInstance(), "used:name", user.getNickname());
            //注册后是否需要将信息保存，使得直接登录，无需再次输入用户认证信息
            return user;
        }catch (Exception e){
            log.error("注册失败了",e);
            return null;
        }
    }


}
