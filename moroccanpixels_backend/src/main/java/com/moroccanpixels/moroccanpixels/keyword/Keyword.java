package com.moroccanpixels.moroccanpixels.keyword;

import com.moroccanpixels.moroccanpixels.image.Image;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Keyword {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "KEYWORD_ID")},
            inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID")}
    )
    private Set<Image> images;

    public Keyword() {
    }

    public Keyword(String name) {
        this.name = name;
    }

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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public void addImage(Image image){
        if (this.images==null) this.images=new HashSet<Image>();
        this.images.add(image);
    }
}
