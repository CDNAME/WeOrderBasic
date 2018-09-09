package com.cd.controller;

import com.cd.VO.ProductInfoVO;
import com.cd.VO.ProductVO;
import com.cd.VO.ResultVO;
import com.cd.dao.ProductInfoDao;
import com.cd.model.ProductCategory;
import com.cd.model.ProductInfo;
import com.cd.service.CategoryService;
import com.cd.service.ProductService;
import com.cd.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * Created by chendeng
 * 2018/8/20 0020 下午 10:21
 */
@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        log.info(productInfoList.toString());

        //2.查询类目(一次性查询)
        /*List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
        for(ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //精简方法（java8,lambda)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(o -> o.getCategoryType())
                .collect(Collectors.toList());
        log.info(categoryTypeList.toString());
        
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        log.info(productCategoryList.toString());
        
        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        log.info(productVOList.toString());
        
        return ResultVOUtil.success(productVOList);
    }
}
