package com.cd.service.impl;

import com.cd.enums.OrderStatusEnum;
import com.cd.enums.PayStatusEnum;
import com.cd.enums.ResultEnum;
import com.cd.exception.SellException;
import com.cd.model.OrderDetail;
import com.cd.model.OrderMaster;
import com.cd.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ChenDeng
 * 2018-08-24 08:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;
    private final String OPENID = "110100";
    private final String ORDER_ID = "1535077370193248876";
    @Test
    public void create() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName("王犯");
        orderMaster.setBuyerPhone("17812345678");
        orderMaster.setBuyerAddress("海师");
        orderMaster.setBuyerOpenid(OPENID);
        
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123458");
        o1.setProductQuantity(2);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123456");
        o2.setProductQuantity(3);
        
        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderMaster.setOrderDetailList(orderDetailList);
        
        OrderMaster result = orderService.create(orderMaster);
        log.info("【创建订单】 result = {}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderMaster result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】 result={}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() {
        //从第一行开始到第三行
        List<OrderMaster> result = orderService.findList(OPENID, 0, 3);
        log.info("【查询订单列表】 result={}", result);
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void cancel() {
        OrderMaster orderMaster= orderService.findOne(ORDER_ID);
        OrderMaster result = orderService.cancel(orderMaster);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderMaster orderMaster= orderService.findOne(ORDER_ID);
        OrderMaster result = orderService.finish(orderMaster);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderMaster orderMaster= orderService.findOne(ORDER_ID);
        OrderMaster result = orderService.paid(orderMaster);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }
}