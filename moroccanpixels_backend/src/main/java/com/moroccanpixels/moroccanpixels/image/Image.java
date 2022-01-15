package com.moroccanpixels.moroccanpixels.image;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moroccanpixels.moroccanpixels.keyword.Keyword;
import com.moroccanpixels.moroccanpixels.user.User;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int downloadCount;
    private int viewCount;

    @Transient
    private int saveCount;

    @Enumerated(EnumType.STRING)
    private ImageType type;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SAVE",
            joinColumns = {@JoinColumn(name = "IMAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private Set<User> savedBy;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "IMAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "KEYWORD_ID")}
    )
    private Set<Keyword> keywords;

    public Image() {
        this.downloadCount=0;
        this.viewCount=0;
        this.savedBy=new HashSet<User>();
        this.keywords=new HashSet<Keyword>();
    }

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
        return "/image/"+this.id;
    }

    public String getLocalPath() {
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

        return this.savedBy.size();
    }

    public void setSaveCount(int saveCount) {
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

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public void addKeyword(Keyword keyword) {
        this.keywords.add(keyword);
    }
}
