package com.moroccanpixels.moroccanpixels.dto;

import com.moroccanpixels.moroccanpixels.entity.ImageType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;

@Component
public class ImageResponseDto {
    private Long id;
    private String owner;
    private String path;
    private Instant uploadedAt;
    private Instant lastModified;
    private String description;
    private int downloadCount;
    private int viewCount;
    private int saveCount;
    private ImageType type;
    private Set<String> savedBy;
    private Set<String> keywords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Instant getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Instant uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(int saveCount) {
        this.saveCount = saveCount;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public Set<String> getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(Set<String> savedBy) {
        this.savedBy = savedBy;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }
}
