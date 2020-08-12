package org.spring.cloud.alibaba.study.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * miaosha_order
 * @author 
 */
@Data
public class MiaoshaOrder implements Serializable {
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 商品ID
     */
    private Long goodsId;

    private static final long serialVersionUID = 1L;
}