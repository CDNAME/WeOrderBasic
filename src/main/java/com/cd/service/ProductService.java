package com.cd.service;

import com.cd.model.Cart;
import com.cd.model.ProductInfo;

import java.util.List;

/**
 * Created by chendeng
 * 2018/8/20 0020 下午 10:52
 */
public interface ProductService {
    Integer save(ProductInfo productInfo, boolean update);

    List<ProductInfo> findUpAll();
    
    ProductInfo findOne(String productId);
    
    //加库存
    void increaseStock(List<Cart> carts);
    
    //减库存
    void decreaseStock(List<Cart> carts);
    
}
