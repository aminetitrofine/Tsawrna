package com.moroccanpixels.moroccanpixels.image;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.moroccanpixels.moroccanpixels.user.User;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Set;

@Entity
@Table
public class Image {
    @Id
    @SequenceGenerator(
            name="image_sequence",
            sequenceName="image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="image_sequence"
    )
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OWNER_ID")
    @NotNull
    private User owner;

    @Transient
    private String path;

    private Instant uploadedAt;
    private Instant lastModified;
    private String description;
    private Long downloadCount;
    private Long viewCount;
    private Long saveCount;

    @Enumerated(EnumType.STRING)
    private ImageType type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SAVE",
            joinColumns = {@JoinColumn(name = "IMAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private Set<User> savedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getPath() {
        return "/uploads/images/"+this.owner.getUsername()+"/"+this.id+"."+this.type.value();
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

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(Long saveCount) {
        this.saveCount = saveCount;
    }

    public Set<User> getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(Set<User> savedBy) {
        this.savedBy = savedBy;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }


}
