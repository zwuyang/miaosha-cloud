package org.spring.cloud.alibaba.study.mapper;


import org.spring.cloud.alibaba.study.dto.MSGoodsDto;
import org.spring.cloud.alibaba.study.entity.Goods;
import org.spring.cloud.alibaba.study.entity.MiaoshaGoods;

import java.util.List;

public interface MiaoshaGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MiaoshaGoods record);

    int insertSelective(MiaoshaGoods record);

    MiaoshaGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaGoods record);

    int updateByPrimaryKey(MiaoshaGoods record);

    List<MSGoodsDto> getAllMiaoShaGoods();
}