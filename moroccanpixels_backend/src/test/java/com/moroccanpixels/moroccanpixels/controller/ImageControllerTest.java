package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.dto.ImageResponseDto;
import com.moroccanpixels.moroccanpixels.mapper.EntityToDto;
import com.moroccanpixels.moroccanpixels.model.ImageType;
import com.moroccanpixels.moroccanpixels.model.StatusType;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.model.entity.Image;
import com.moroccanpixels.moroccanpixels.model.entity.Keyword;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;
import com.moroccanpixels.moroccanpixels.service.ImageService;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageControllerTest {
    private final ImageService imageService = mock(ImageService.class);

    @Test
    void shouldListImages() {
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image1 = new Image(5L,user,"my_path", Instant.now(),Instant.now(),"description1",0, new Category(), 0, 0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        Image image2 = new Image(6L,user,"my_path", Instant.now(),Instant.now(),"description2",0, new Category(), 0, 0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());

        List<Image> imageList = new ArrayList<>();
        imageList.add(image1); imageList.add(image2);

        Set<ImageResponseDto> imageResponseDtoSet = new HashSet<>();
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image1));
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image2));

        when(imageService.listImages()).thenReturn(imageResponseDtoSet);

        ImageController imageController = new ImageController(imageService);
        assertEquals(imageResponseDtoSet,imageController.listImages());
    }

    @Test
    void shouldGetImage() {
        User user = new User("username","user@gmail.com","first name",
                "last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(5L,user,"my_path", Instant.now(),Instant.now(),
                "description", 0, new Category(), 0, 0,
                ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());

        ImageResponseDto imageResponseDto = EntityToDto.imageEntityToDto(image);
        when(imageService.getImage(5L)).thenReturn(imageResponseDto);

        ImageController imageController = new ImageController(imageService);
        assertEquals(imageResponseDto,imageController.getImage(5L));
    }

    @Test
    void shouldDeleteImage() {
        User user = new User("username","user@gmail.com","first name",
                "last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(1L,user,"image_path", Instant.now(),Instant.now(),
                "description",0,new Category(),0,0,
                ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());

        imageService.deleteImage(image.getId());
        verify(imageService).deleteImage(image.getId());

        ImageController imageController = new ImageController(imageService);
        imageController.deleteImage(image.getId());
    }

    @Test
    void shouldMapKeywordToImage() {
        User user = new User("username","user@gmail.com","first name",
                "last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(1L,user,"image_path", Instant.now(),Instant.now(),
                "description",0,new Category(),0,0,
                ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        Keyword keyword = new Keyword("sea");

        imageService.mapKeywordToImage(image.getId(), keyword.getName());
        verify(imageService).mapKeywordToImage(image.getId(), keyword.getName());
        ImageController imageController = new ImageController(imageService);
        imageController.mapKeywordToImage(image.getId(), keyword.getName());
    }
}