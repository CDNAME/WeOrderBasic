package com.cd.service.impl;

import com.cd.model.ProductCategory;
import com.cd.model.ProductInfo;
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
 * 2018/8/22 0022 下午 3:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void save() {
        ProductInfo productInfo= new ProductInfo();
        productInfo.setProductId("123490");
        productInfo.setProductName("小龙虾");
        productInfo.setProductPrice(new BigDecimal(20.0));
        productInfo.setProductStock(50);
        productInfo.setProductDescription("爆炒麻辣");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        int result = productService.save(productInfo, false);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        log.info(productInfoList.toString());
        Assert.assertNotEquals(0, productInfoList.size());
    }
    
    @Test
    public void finOne() {
        ProductInfo productInfo = productService.findOne("123456");
        log.info(productInfo.toString());
        Assert.assertNotEquals(null, productInfo);
    }
}