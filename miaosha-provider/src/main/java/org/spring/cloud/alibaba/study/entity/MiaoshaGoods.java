package org.spring.cloud.alibaba.study.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * miaosha_goods
 * @author 
 */
@Data
public class MiaoshaGoods implements Serializable {
    /**
     * 秒杀的商品表
     */
    private Long id;

    /**
     * 商品Id
     */
    private Long goodsId;

    /**
     * 秒杀价
     */
    private BigDecimal miaoshaPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;

    private static final long serialVersionUID = 1L;
}