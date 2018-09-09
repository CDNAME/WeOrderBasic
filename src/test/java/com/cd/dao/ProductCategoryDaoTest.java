package com.cd.dao;

import com.cd.model.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryDaoTest {
    
    @Autowired
    private ProductCategoryDao dao;

    @Test
    public void selectOne() {
        ProductCategory productCategory = dao.selectOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void selectAll() {
        List<ProductCategory> categoryList = dao.selectAll();
        for (ProductCategory productCategory: categoryList) {
            log.info(productCategory.toString());
        }

    }

    @Test
    public void selectByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(2,3,10);
        List<ProductCategory> result = dao.selectByCategoryTypeIn(list);
        for(ProductCategory productCategory : result) {
            log.info(productCategory.toString());
        }
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void insert() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        int i = dao.insert(productCategory);
        log.info("执行结果"+i);
    }

    @Test
    public void insert1() {
        ProductCategory productCategory = new ProductCategory("男生最爱",3);
        productCategory.setCategoryId(8);
        int i = dao.insert(productCategory);
        Assert.assertEquals(1, i);
    }

    @Test
    public void update() {
        ProductCategory productCategory = new ProductCategory("男生最爱",3);
        productCategory.setCategoryId(10);
        int i = dao.update(productCategory);
        log.info("执行结果"+i);
    }
}