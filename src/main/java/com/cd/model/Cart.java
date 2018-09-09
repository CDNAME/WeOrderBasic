package com.cd.model;

import lombok.Data;

/**
 * Created by ChenDeng
 * 2018-08-23 22:04
 */
@Data
public class Cart {
    private String productId;
    private Integer productQuantity;

    public Cart() {
    }

    public Cart(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
