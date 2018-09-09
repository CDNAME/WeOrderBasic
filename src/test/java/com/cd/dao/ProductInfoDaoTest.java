package com.cd.dao;

import com.cd.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by chendeng
 * 2018/8/20 0020 下午 5:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao dao;
    
    @Test
    public void insertTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123458");
        productInfo.setProductName("芒果粥");
        productInfo.setProductPrice(new BigDecimal(9.0));
        productInfo.setProductStock(80);
        productInfo.setProductDescription("冰凉芒果");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        int result = dao.insert(productInfo);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("巨人汉堡");
        productInfo.setProductPrice(new BigDecimal(8.5));
        productInfo.setProductStock(80);
        productInfo.setProductDescription("吃了能变巨人");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);

        int result = dao.update(productInfo);
        Assert.assertEquals(1,result);
    }
    
    @Test
    public void selectByProductId() {
        ProductInfo productInfo = dao.selectByProductId("123456");
        log.info(productInfo.toString());
        Assert.assertNotEquals(null, productInfo);
    }
    
    @Test
    public void selectByProductStatus() throws Exception {
        List<ProductInfo> productInfoList = dao.selectByProductStatus(0);
        log.info(productInfoList.toString());
        Assert.assertNotEquals(0, productInfoList.size());
    }
}