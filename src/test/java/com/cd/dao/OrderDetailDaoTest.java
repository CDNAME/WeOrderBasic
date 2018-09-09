package com.cd.dao;

import com.cd.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by chendeng
 * 2018/8/23 0023 20 20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao dao;
    
    @Test
    public void insert() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456778");
        orderDetail.setOrderId("1234579");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductPrice(new BigDecimal(4.2));
        orderDetail.setProductQuantity(2);

        int result = dao.insert(orderDetail);
        Assert.assertEquals(1, result);
    }

    @Test
    public void update() {
    }

    @Test
    public void selectByOrderId() {
        List<OrderDetail> orderDetailList = dao.selectByOrderId("1234579");
        log.info(orderDetailList.toString());
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}