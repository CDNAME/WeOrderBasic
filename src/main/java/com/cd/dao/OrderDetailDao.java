package com.cd.dao;

import com.cd.model.OrderDetail;
import com.cd.model.OrderMaster;

import java.util.List;

/**
 * Created by chendeng
 * 2018/8/22 0022 下午 5:08
 */
public interface OrderDetailDao {
    Integer insert(OrderDetail orderDetail);
    
    Integer update(OrderDetail orderDetail);
    
    List<OrderDetail> selectByOrderId(String orderId);
}
