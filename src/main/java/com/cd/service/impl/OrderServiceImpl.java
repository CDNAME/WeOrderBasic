package com.cd.service.impl;

import com.cd.dao.OrderDetailDao;
import com.cd.dao.OrderMasterDao;
import com.cd.enums.OrderStatusEnum;
import com.cd.enums.PayStatusEnum;
import com.cd.enums.ResultEnum;
import com.cd.exception.SellException;
import com.cd.model.Cart;
import com.cd.model.OrderDetail;
import com.cd.model.OrderMaster;
import com.cd.model.ProductInfo;
import com.cd.service.OrderService;
import com.cd.service.ProductService;
import com.cd.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ChenDeng
 * 2018-08-23 20:57
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderMasterDao masterDao;
    
    @Autowired
    private OrderDetailDao detailDao;
    
    @Autowired
    private ProductService productService;
    
    @Override
    @Transactional
    public OrderMaster create(OrderMaster orderMaster) {
        
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //List<Cart> cartList = new ArrayList<>();
        
        //1.查询商品（数量，价格）
        for(OrderDetail orderDetail1 : orderMaster.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail1.getProductId());
            if(productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail1.getProductQuantity()))
                    .add(orderAmount);
            
            //3.订单详情入库(OrderDetail)
            BeanUtils.copyProperties(productInfo, orderDetail1);
            orderDetail1.setDetailId(KeyUtil.getUniqueKey());
            orderDetail1.setOrderId(orderId);
            detailDao.insert(orderDetail1);
            
            //Cart cart = new Cart(orderDetail1.getProductId(), orderDetail1.getProductQuantity());
            //cartList.add(cart);
            
        }
        //4.写入订单数据库（OrderMaster）
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        masterDao.insert(orderMaster);
        
        //5.扣库存
        List<Cart> cartList = orderMaster.getOrderDetailList().stream().map(e ->
            new Cart(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartList);
        
        return orderMaster;
    }

    @Override
    public OrderMaster findOne(String orderId) {
        OrderMaster orderMaster = masterDao.selectByOrderId(orderId);
        if(orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = detailDao.selectByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        orderMaster.setOrderDetailList(orderDetailList);
        return orderMaster;
    }

    @Override
    public List<OrderMaster> findList(String buyerOpenid, Integer startPoint, Integer endPoint) {
        return masterDao.selectByBuyerOpenid(buyerOpenid,startPoint,endPoint);
    }

    @Override
    @Transactional
    public OrderMaster cancel(OrderMaster orderMaster) {
        //判断订单状态
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确，orderID={},orderStatus={}",orderMaster.getOrderId(), orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        int updateResult = masterDao.updateOrderStatus(orderMaster);
        if(updateResult == 0) {
            log.error("【取消订单】更新失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返还库存
        if(CollectionUtils.isEmpty(orderMaster.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<Cart> cartList = orderMaster.getOrderDetailList().stream()
                .map(e -> new Cart(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartList);
        
        //如果已支付，需要退款
        if(orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO Refund
        }
        return orderMaster;
    }

    @Override
    public OrderMaster finish(OrderMaster orderMaster) {
        //判断订单状态
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderID={},orderStatus={}",orderMaster.getOrderId(), orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        
        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        int updateResult = masterDao.updateOrderStatus(orderMaster);
        if(updateResult == 0) {
            log.error("【完结订单】更新失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderMaster;
    }

    @Override
    @Transactional
    public OrderMaster paid(OrderMaster orderMaster) {
        //判断订单状态
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确，orderID={},orderStatus={}",orderMaster.getOrderId(), orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付态
        if(!orderMaster.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】订单支付状态不正确，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        Integer updateResult = masterDao.updatePayStatus(orderMaster);
        if(updateResult == 0) {
            log.error("【支付订单】更新失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderMaster;
    }
}
