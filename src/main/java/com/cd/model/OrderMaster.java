package com.cd.model;

import com.cd.enums.OrderStatusEnum;
import com.cd.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by chendeng
 * 2018/8/22 0022 下午 4:45
 */
@Data
public class OrderMaster {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 支付状态, 默认为0未支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    private Date updateTime;
    
    private List<OrderDetail> orderDetailList;
}
