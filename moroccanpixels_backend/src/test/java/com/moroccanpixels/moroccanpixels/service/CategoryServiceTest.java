package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.config.ImageConfig;
import com.moroccanpixels.moroccanpixels.dto.CategoryRequestDto;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    private final CategoryRepository categoryRepository = mock(CategoryRepository.class);
    private final ImageConfig imageConfig = mock(ImageConfig.class);

    @Test
    void shouldAddCategory() {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("category_name");
        categoryRequestDto.setDescription("category_desc");

        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        assertThat(category.getName()).isEqualTo(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        assertThat(category.getDescription()).isEqualTo(categoryRequestDto.getDescription());

        categoryRepository.save(category);
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        assertThat(categoryArgumentCaptor.getValue()).isEqualTo(category);
    }
}