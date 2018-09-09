package com.cd.model;

import com.cd.enums.ProductStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 */

@Data
public class ProductInfo {
    private String productId;
    
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;   //库存
    private String productDescription;      //描述
    private String productIcon;     //小图
    private Integer productStatus = ProductStatusEnum.UP.getCode();  //状态,0正常，1下架
    private Integer categoryType;   //类目编号
    private Date updateTime;
}
