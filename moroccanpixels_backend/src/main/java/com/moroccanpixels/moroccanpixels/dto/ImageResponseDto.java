package com.moroccanpixels.moroccanpixels.dto;

import com.moroccanpixels.moroccanpixels.model.ImageType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;

@Component
@Data
public class ImageResponseDto {
    private Long id;
    private String owner;
    private String filePath;
    private Instant uploadedAt;
    private Instant lastModified;
    private String description;
    private int downloadCount;
    private int viewCount;
    private int saveCount;
    private ImageType type;
    private Set<String> keywords;
    private boolean saved;
}
