package org.spring.cloud.alibaba.study.mapper;


import org.spring.cloud.alibaba.study.entity.MiaoshaOrder;

public interface MiaoshaOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MiaoshaOrder record);

    int insertSelective(MiaoshaOrder record);

    MiaoshaOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaOrder record);

    int updateByPrimaryKey(MiaoshaOrder record);
}