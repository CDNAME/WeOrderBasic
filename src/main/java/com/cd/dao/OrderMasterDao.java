package com.cd.dao;

import com.cd.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by chendeng
 * 2018/8/22 0022 下午 5:01
 */
public interface OrderMasterDao {
    Integer insert(OrderMaster orderMaster);
    
    Integer updatePayStatus(OrderMaster orderMaster);
    
    Integer updateOrderStatus(OrderMaster orderMaster);

    List<OrderMaster> selectByBuyerOpenid(String buyerOpenid, Integer startPoint, Integer endPoint);

    OrderMaster selectByOrderId(String orderId);
}
