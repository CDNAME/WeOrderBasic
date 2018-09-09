package com.cd.enums;

import lombok.Getter;

/**
 * Created by chendeng
 * 2018/8/22 0022 下午 4:49
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;
    private Integer code;
    
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
