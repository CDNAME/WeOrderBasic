package com.cd.dao;

import com.cd.model.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by chendeng
 * 2018/8/23 0023 09 04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao dao;
    
    private final String OPENID = "110100";
    @Test
    public void insert() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234579");
        orderMaster.setBuyerName("王犯");
        orderMaster.setBuyerPhone("17812345678");
        orderMaster.setBuyerAddress("海师");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(10.0));

        int result = dao.insert(orderMaster);
        Assert.assertEquals(1,result);
    }
    
    @Test
    public void updatePayStatus() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234579");
        orderMaster.setPayStatus(1);
        
        int result = dao.updatePayStatus(orderMaster);
        Assert.assertEquals(1,result);
        
    }

    @Test
    public void updateOrderStatus() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234579");
        orderMaster.setOrderStatus(1);
        
        int result = dao.updateOrderStatus(orderMaster);
        Assert.assertEquals(1,result);
        
    }

    @Test
    public void selectByBuyerOpenid() {
        List<OrderMaster> result = dao.selectByBuyerOpenid(OPENID, 0,2);
        log.info(result.toString());
        Assert.assertNotEquals(0, result.size());
    }
}