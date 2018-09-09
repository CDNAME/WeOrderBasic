package com.cd.service.impl;

import com.cd.dao.ProductCategoryDao;
import com.cd.model.ProductCategory;
import com.cd.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryDao dao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return dao.selectOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return dao.selectAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return dao.selectByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public Integer save(ProductCategory productCategory, boolean update) {
        if(update == true) {
            return dao.update(productCategory);
        }
        return dao.insert(productCategory);
    }
}
