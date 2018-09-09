package com.cd.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCategory {
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;   //类目编号
    private Date createTime;
    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
