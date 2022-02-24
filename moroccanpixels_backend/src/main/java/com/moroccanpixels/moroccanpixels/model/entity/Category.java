package com.moroccanpixels.moroccanpixels.model.entity;

import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.util.Collection;


@Entity
@NoArgsConstructor @AllArgsConstructor @Table @Setter @Getter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Unique
    private String name;
    private String description;
    private String imagePath;
    @OneToMany(mappedBy = "category")
    private Collection<Image> images;


}
