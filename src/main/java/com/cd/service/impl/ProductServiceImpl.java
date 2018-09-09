package com.cd.service.impl;

import com.cd.dao.ProductInfoDao;
import com.cd.enums.ResultEnum;
import com.cd.exception.SellException;
import com.cd.model.Cart;
import com.cd.model.ProductInfo;
import com.cd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chendeng
 * 2018/8/20 0020 下午 10:54
 */
@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductInfoDao dao;
    
    @Override
    public Integer save(ProductInfo productInfo, boolean update) {
        if(update == true) {
            return dao.update(productInfo);
        }
        return dao.insert(productInfo);
    }

    @Override
    public ProductInfo findOne(String productId) {
        return dao.selectByProductId(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return dao.selectByProductStatus(0);
    }

    @Override
    @Transactional
    public void increaseStock(List<Cart> carts) {
        for(Cart cart : carts) {
            ProductInfo productInfo = dao.selectByProductId(cart.getProductId());
            if(productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cart.getProductQuantity();
            productInfo.setProductStock(result);
            dao.update(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<Cart> carts) {
        for(Cart cart : carts) {
            ProductInfo productInfo = dao.selectByProductId(cart.getProductId());
            if(productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() - cart.getProductQuantity();
            if(result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            
            dao.update(productInfo);
        }
    }
}
