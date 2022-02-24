package com.moroccanpixels.moroccanpixels.controller;


import com.moroccanpixels.moroccanpixels.dto.CategoryRequestDto;
import com.moroccanpixels.moroccanpixels.dto.CategoryResponseDto;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("")

public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("category")
    public Category addCategory(@ModelAttribute CategoryRequestDto category){
        return categoryService.addCategory(category);
    }
    @GetMapping("category")
    public List<CategoryResponseDto> listCategories(){
        return categoryService.listCategories();
    }

    @PostMapping("category/{categoryId}")
    public void setCategoryImage(@PathVariable Long categoryId, @RequestBody MultipartFile categoryImage){
        categoryService.setCategoryImage(categoryId,categoryImage);
    }

    @GetMapping(path="category/{categoryId}/view",
            produces = {IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE}
    )
    public @ResponseBody byte[] viewCategoryImage(@PathVariable Long categoryId){
        return categoryService.viewCategoryImage(categoryId);
    }
}
