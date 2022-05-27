package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.dto.CategoryRequestDto;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryControllerTest {
    private final CategoryService categoryService = Mockito.mock(CategoryService.class);

    @Test
    void shouldAddCategory() {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("category_name");
        categoryRequestDto.setDescription("category_desc");
        Category category = new Category();
        category.setName(categoryRequestDto.getName());

        when(categoryService.addCategory(categoryRequestDto)).thenReturn(category);
        CategoryController categoryController = new CategoryController(categoryService);
        assertEquals(category,categoryController.addCategory(categoryRequestDto));

    }
}