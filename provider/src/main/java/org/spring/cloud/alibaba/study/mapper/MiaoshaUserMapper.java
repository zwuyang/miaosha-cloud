package org.spring.cloud.alibaba.study.mapper;


import org.spring.cloud.alibaba.study.entity.MiaoshaUser;

public interface MiaoshaUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MiaoshaUser record);

    int insertSelective(MiaoshaUser record);

    MiaoshaUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaUser record);

    int updateByPrimaryKey(MiaoshaUser record);

    MiaoshaUser getByNickName(String nickname);
}