package com.cd.controller;

import com.cd.VO.ResultVO;
import com.cd.enums.ResultEnum;
import com.cd.exception.SellException;
import com.cd.form.OrderForm;
import com.cd.model.OrderMaster;
import com.cd.service.OrderService;
import com.cd.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by ChenDeng
 * 2018-08-24 14:31
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    //创建订单
    public ResultVO<Map<String, String>> create(@Validated OrderForm orderForm,
                                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

//        OrderMaster orderMaster = 
//        orderService.create()
        
        return null;
    }
    //订单列表
    
    //订单详情
    
    //取消订单
}
