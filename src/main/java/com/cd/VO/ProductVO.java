package com.cd.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目）
 * Created by chendeng
 * 2018/8/20 0020 下午 10:33
 */
@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;
    
    @JsonProperty("type")
    private Integer categoryType;
    
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList; 
    
}
