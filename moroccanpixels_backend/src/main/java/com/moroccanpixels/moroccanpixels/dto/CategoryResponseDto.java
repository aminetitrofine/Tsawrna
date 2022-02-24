package com.moroccanpixels.moroccanpixels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
public class CategoryResponseDto {
    private String name;
    private String description;
    private Set<ImageResponseDto> images;



}
