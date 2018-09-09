package com.cd.service;

import com.cd.model.OrderMaster;
import com.cd.model.OrderMaster;

import java.util.List;

/**
 * Created by ChenDeng
 * 2018-08-23 20:47
 */
public interface OrderService {
    OrderMaster create(OrderMaster orderMaster);
    
    OrderMaster findOne(String orderId);
    
    List<OrderMaster> findList(String buyerOpenid, Integer startPoint, Integer endPoint);
    
    OrderMaster cancel(OrderMaster OrderMaster);
    
    OrderMaster finish(OrderMaster OrderMaster);
    
    OrderMaster paid(OrderMaster OrderMaster);
    
}
