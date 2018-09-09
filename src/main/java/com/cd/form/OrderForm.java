package com.cd.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by ChenDeng
 * 2018-08-24 14:35
 */
@Data
public class OrderForm {
    //买家信息
    
    @NotNull(message = "姓名必填")
    private String name;

    @NotNull(message = "手机号必填")
    private String phone;
    
    @NotNull(message = "地址必填")
    private String address;
    
    @NotNull(message = "openid必填")
    private String openid;
    
    @NotNull(message = "购物车不能为空")
    private String items;
}
