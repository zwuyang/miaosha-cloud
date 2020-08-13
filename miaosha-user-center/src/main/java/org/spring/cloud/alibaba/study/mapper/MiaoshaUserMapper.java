package org.spring.cloud.alibaba.study.mapper;


import org.spring.cloud.alibaba.study.entity.MiaoshaUser;

import java.util.List;

public interface MiaoshaUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(MiaoshaUser record);

    int insertSelective(MiaoshaUser record);

    MiaoshaUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MiaoshaUser record);

    int updateByPrimaryKey(MiaoshaUser record);

    MiaoshaUser getByNickName(String nickname);

    List<String> getUsedUserNames();
}