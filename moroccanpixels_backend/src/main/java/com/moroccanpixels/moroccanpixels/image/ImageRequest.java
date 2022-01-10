package com.moroccanpixels.moroccanpixels.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class ImageRequest {
    @JsonProperty("file")
    private MultipartFile file;
    @JsonProperty("username")
    private String username;
    @JsonProperty("description")
    private String description;

    public ImageRequest() {
    }

    public ImageRequest(MultipartFile file, String username, String description) {
        this.file = file;
        this.username = username;
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
