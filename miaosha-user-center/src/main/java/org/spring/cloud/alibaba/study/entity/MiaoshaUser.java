package org.spring.cloud.alibaba.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.spring.cloud.alibaba.study.dto.UserInfoDto;

import java.io.Serializable;
import java.util.Date;

/**
 * miaosha_user
 * @author 
 */
@Data
@AllArgsConstructor
public class MiaoshaUser implements Serializable {
    /**
     * 用户ID，手机号码
     */
    private String id;

    private String nickname;

    /**
     * MD5(MD5(pass明文+固定salt) + salt)
     */
    private String password;

    private String salt;

    /**
     * 头像，云存储的ID
     */
    private String head;

    /**
     * 注册时间
     */
    private Date registerDate;

    /**
     * 上蔟登录时间
     */
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    private Integer loginCount;

    private static final long serialVersionUID = 1L;

    public MiaoshaUser(UserInfoDto userInfoDto) {
        this.id = userInfoDto.getId();
        this.nickname = userInfoDto.getNickName();
        this.password = userInfoDto.getPassword();
        this.registerDate = userInfoDto.getCreateDate();
        this.salt = userInfoDto.getSalt();
    }
}