package com.moroccanpixels.moroccanpixels.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class ImageRequestDto {
    @JsonProperty("file")
    private MultipartFile file;
    @JsonProperty("description")
    private String description;

    public ImageRequestDto() {
    }

    public ImageRequestDto(MultipartFile file , String description) {
        this.file = file;
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
