package com.cd.controller;

import com.cd.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCategoryController {
    @Autowired
    CategoryService categoryService;
    
    @GetMapping("/sell/categorys")
    public Object productCategorys() {
        return categoryService.findAll(); 
    }
}
