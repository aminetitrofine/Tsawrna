package com.moroccanpixels.moroccanpixels.service;


import com.moroccanpixels.moroccanpixels.dto.CategoryRequestDto;
import com.moroccanpixels.moroccanpixels.dto.CategoryResponseDto;
import com.moroccanpixels.moroccanpixels.dto.ImageResponseDto;
import com.moroccanpixels.moroccanpixels.mapper.EntityToDto;
import com.moroccanpixels.moroccanpixels.model.entity.Billing;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<CategoryResponseDto> listCategories() {

        return categoryRepository.findAll().stream()
                .map((category ->
                    new CategoryResponseDto(
                           category.getName(),
                           category.getDescription(),
                           EntityToDto.imageEntityToDto(category.getImages())
                   )
                )).collect(Collectors.toList());
    }

}
