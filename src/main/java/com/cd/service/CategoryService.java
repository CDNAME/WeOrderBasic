package com.cd.service;

import com.cd.model.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    
    List<ProductCategory> findAll();
    
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    
    Integer save(ProductCategory productCategory, boolean update);
}
