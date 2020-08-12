package org.spring.cloud.alibaba.study.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * order_info
 * @author 
 */
@Data
public class OrderInfo implements Serializable {
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 收获地址ID
     */
    private Long deliveryAddrId;

    /**
     * 冗余过来的商品名称
     */
    private String goodsName;

    /**
     * 商品数量
     */
    private Integer goodsCount;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 1pc，2android，3ios
     */
    private Byte orderChannel;

    /**
     * 订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     */
    private Byte status;

    /**
     * 订单的创建时间
     */
    private Date createDate;

    /**
     * 支付时间
     */
    private Date payDate;

    private static final long serialVersionUID = 1L;
}