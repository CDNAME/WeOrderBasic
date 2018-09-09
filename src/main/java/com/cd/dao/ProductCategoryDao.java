package com.cd.dao;

import com.cd.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    ProductCategory selectOne(Integer categoryId);

    List<ProductCategory> selectAll();

    List<ProductCategory> selectByCategoryTypeIn(List<Integer> categoryTypeList);

    Integer insert(ProductCategory productCategory);
    
    Integer update(ProductCategory productCategory);
}