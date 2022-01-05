package com.learn.demo.love;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gaobin
 * @date 2021/9/29 10:10 上午
 * @desc
 */
@Data
public class BbsModel {

    private BigDecimal money;

    /**
     * 商品件数
     */
    private int shopNum;

}
