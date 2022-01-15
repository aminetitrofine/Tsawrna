package com.moroccanpixels.moroccanpixels.mapper;

import com.moroccanpixels.moroccanpixels.dto.*;
import com.moroccanpixels.moroccanpixels.image.Image;
import com.moroccanpixels.moroccanpixels.keyword.Keyword;
import com.moroccanpixels.moroccanpixels.user.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityToDto {
    public static KeywordDto keywordEntityToDto(Keyword keyword){
        KeywordDto dto = new KeywordDto();
        dto.setId(keyword.getId());
        dto.setName(keyword.getName());
        dto.setImages(keyword.getImages().stream().map(Image::getPath).collect(Collectors.toSet()));
        return dto;
    }
    public static Set<KeywordDto> keywordEntityToDto(Collection<Keyword> keywords){
        return keywords.stream().map(EntityToDto::keywordEntityToDto).collect(Collectors.toSet());
    }
    public static ImageDto ImageEntityToDto(Image image){
        ImageDto dto = new ImageDto();
        dto.setId(image.getId());
        dto.setDescription(image.getDescription());
        dto.setDownloadCount(image.getDownloadCount());
        dto.setLastModified(image.getLastModified());
        dto.setUploadedAt(image.getUploadedAt());
        dto.setType(image.getType());
        dto.setOwner(image.getOwner().getUsername());
        dto.setPath(image.getPath());
        dto.setSaveCount(image.getSaveCount());
        dto.setSavedBy(image.getSavedBy().stream().map(User::getUsername).collect(Collectors.toSet()));
        dto.setKeywords(image.getKeywords().stream().map(Keyword::getName).collect(Collectors.toSet()));
        return dto;
    }
    public static Set<ImageDto> ImageEntityToDto(Collection<Image> images){
        return images.stream().map(EntityToDto::ImageEntityToDto).collect(Collectors.toSet());
    }

    public static UserDto UserEntityToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setBirthdate(user.getBirthdate());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setImages(user.getImages().stream().map(Image::getPath).collect(Collectors.toSet()));
        dto.setSavedImages(user.getSavedImages().stream().map(Image::getPath).collect(Collectors.toSet()));
        return dto;
    }
}
