package com.moroccanpixels.moroccanpixels.service;


import com.moroccanpixels.moroccanpixels.dto.CategoryRequestDto;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category addCategory(CategoryRequestDto dtoCategory) {
        Category category = new Category();
        category.setName(dtoCategory.getName());
        category.setDescription(dtoCategory.getDescription());
        return categoryRepository.save(category);
    }
}
