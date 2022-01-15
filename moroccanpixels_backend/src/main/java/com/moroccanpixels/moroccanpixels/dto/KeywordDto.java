package com.moroccanpixels.moroccanpixels.dto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class KeywordDto {
    private Long id;
    private String name;
    private Set<String> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }
}
