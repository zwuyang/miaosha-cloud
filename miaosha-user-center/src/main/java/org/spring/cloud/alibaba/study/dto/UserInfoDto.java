package org.spring.cloud.alibaba.study.dto;

import lombok.Data;
import org.spring.cloud.alibaba.study.common.util.MD5Util;
import java.util.Date;
import java.util.UUID;

/**
 * @author yzw
 * @Classname UserInfoDto
 * @Description TODO
 * @Date 2020/8/13 10:30
 */
@Data
public class UserInfoDto {

    private String id;

    private String salt;

    private String userName;

    private String password;

    private String nickName;

    private Date createDate;

    public UserInfoDto(UserRegisterDto registerDto){
        createUserInfo(registerDto);
    }

    private void createUserInfo(UserRegisterDto registerDto) {
        this.nickName = registerDto.getNickName();
        this.id = createUserName();
        this.salt = createSalt(id);
        this.password = encodePass(registerDto.getPassword(), salt);
        this.createDate = new Date();
    }


    private String createSalt(String str) {
        if (str == null || str.length() == 0){
            return "abcdefg";
        }else {
            return str.substring(7, 14);
        }
    }

    private String createUserName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private String encodePass(String originalPass, String salt) {
        return MD5Util.encode(originalPass, salt);
    }



}
