package com.moroccanpixels.moroccanpixels.mapper;

import com.moroccanpixels.moroccanpixels.dto.*;
import com.moroccanpixels.moroccanpixels.model.entity.Image;
import com.moroccanpixels.moroccanpixels.model.entity.Keyword;
import com.moroccanpixels.moroccanpixels.model.entity.User;

import java.util.Collection;
import java.util.List;
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
    public static ImageResponseDto imageEntityToDto(Image image){
        ImageResponseDto dto = new ImageResponseDto();
        dto.setId(image.getId());
        dto.setDescription(image.getDescription());
        dto.setLastModified(image.getLastModified());
        dto.setUploadedAt(image.getUploadedAt());
        dto.setType(image.getType());
        dto.setOwner(image.getOwner().getUsername());
        dto.setPath(image.getPath());
        dto.setDownloadCount(image.getDownloadCount());
        dto.setSaveCount(image.getSaveCount());
        dto.setViewCount(image.getViewCount());
        dto.setKeywords(image.getKeywords().stream().map(Keyword::getName).collect(Collectors.toSet()));
        return dto;
    }
    public static Set<ImageResponseDto> imageEntityToDto(Collection<Image> images){
        return images.stream().map(EntityToDto::imageEntityToDto).collect(Collectors.toSet());
    }

    public static UserResponseDto userToUserResponseDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthdate(user.getBirthdate());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setImages(user.getImages().stream().map(Image::getPath).collect(Collectors.toSet()));
        dto.setSavedImages(user.getSavedImages().stream().map(Image::getPath).collect(Collectors.toSet()));
        return dto;
    }
    public static List<UserResponseDto> userToUserResponseDto(Collection<User> users){
        return users.stream().map(EntityToDto::userToUserResponseDto).collect(Collectors.toList());
    }
}
