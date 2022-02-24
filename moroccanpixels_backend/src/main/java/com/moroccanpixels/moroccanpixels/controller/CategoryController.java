package com.moroccanpixels.moroccanpixels.controller;


import com.moroccanpixels.moroccanpixels.dto.CategoryRequestDto;
import com.moroccanpixels.moroccanpixels.dto.CategoryResponseDto;
import com.moroccanpixels.moroccanpixels.model.entity.Billing;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")

public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category addCategory(@ModelAttribute CategoryRequestDto category){
        return categoryService.addCategory(category);
    }
    @GetMapping
    public List<CategoryResponseDto> listCategories(){
        return categoryService.listCategories();
    }
}
