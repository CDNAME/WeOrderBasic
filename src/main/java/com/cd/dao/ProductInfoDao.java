package com.cd.dao;

import com.cd.model.ProductCategory;
import com.cd.model.ProductInfo;

import java.util.List;

/**
 * Created by chendeng
 * 2018/8/20 0020 下午 5:21
 */
public interface ProductInfoDao {
    
    Integer insert(ProductInfo productInfo);

    Integer update(ProductInfo productInfo);

    List<ProductInfo> selectByProductStatus(Integer productStatus);

    ProductInfo selectByProductId(String productId);
}
