package com.moroccanpixels.moroccanpixels.service;


import com.moroccanpixels.moroccanpixels.config.ImageConfig;
import com.moroccanpixels.moroccanpixels.dto.CategoryRequestDto;
import com.moroccanpixels.moroccanpixels.dto.CategoryResponseDto;
import com.moroccanpixels.moroccanpixels.mapper.EntityToDto;
import com.moroccanpixels.moroccanpixels.model.ImageType;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.repository.CategoryRepository;
import com.moroccanpixels.moroccanpixels.utils.ImageUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ImageConfig imageConfig;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ImageConfig imageConfig) {
        this.categoryRepository = categoryRepository;
        this.imageConfig = imageConfig;
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
                           EntityToDto.imageEntityToDto(category.getImages()),
                           "/category/"+category.getId()+"/view"
                   )
                )).collect(Collectors.toList());
    }

    public byte[] viewCategoryImage(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("category " + categoryId + " not found"));
        InputStream in;
        try{
            in = new FileInputStream(category.getImagePath());
            return IOUtils.toByteArray(in);
        }catch(IOException e1){
            e1.printStackTrace();
        }
        return new byte[0];
    }

    @Transactional
    public void setCategoryImage(Long categoryId, MultipartFile categoryImage) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("category " + categoryId + " not found"));
        //saving file
        String directory = String.format("%s/categories/",imageConfig.getDirectory());
        String fileName = String.format("%s.%s",category.getName(), Objects.requireNonNull(ImageType.fromContentType(categoryImage.getContentType())).value());
        ImageUtils.saveImage(categoryImage,directory,fileName);
        category.setImagePath(directory+fileName);
    }
}
