package com.cd.enums;

import lombok.Getter;

/**
 * Created by chendeng
 * 2018/8/22 0022 下午 5:02
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "上架"),
    DOWN(1, "下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
