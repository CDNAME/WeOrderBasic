package com.cd.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 * Created by chendeng
 * 2018/8/20 0020 下午 10:36
 */
@Data
public class ProductInfoVO {
    
    @JsonProperty("id")
    private String productId;
    
    @JsonProperty("name")
    private String productName;
    
    @JsonProperty("price")
    private BigDecimal productPrice;
    
    @JsonProperty("description")
    private String productDescription;
    
    @JsonProperty("icon")
    private String productIcon;
}
