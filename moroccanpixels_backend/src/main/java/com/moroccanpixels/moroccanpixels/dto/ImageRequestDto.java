package com.moroccanpixels.moroccanpixels.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestDto {
    private MultipartFile file;
    private String description;
}
