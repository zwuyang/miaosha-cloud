package org.spring.cloud.alibaba.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yzw
 * @Classname GoodsDto
 * @Description TODO
 * @Date 2020/8/12 15:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MSGoodsDto {

    private Double msPrice;

    private int stockCount;

    private Date startTime;

    private Date endTime;

    private Long id;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private String goodsDetail;

    private Double goodsPrice;

    private int goodsStock;



}
