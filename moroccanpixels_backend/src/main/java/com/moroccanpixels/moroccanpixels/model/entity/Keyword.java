package com.moroccanpixels.moroccanpixels.model.entity;

import com.moroccanpixels.moroccanpixels.model.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Keyword(String name) {
        this.name = name;
    }

    public void addImage(Image image){
        if (this.images==null) this.images=new HashSet<Image>();
        this.images.add(image);
    }
}
