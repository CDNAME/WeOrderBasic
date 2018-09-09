package com.cd.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chendeng
 * 2018/8/22 0022 下午 4:58
 */
@Data
public class OrderDetail {
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
    private Date updateTime;
}
