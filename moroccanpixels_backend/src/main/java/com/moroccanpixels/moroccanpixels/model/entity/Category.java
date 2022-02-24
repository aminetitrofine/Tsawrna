package com.moroccanpixels.moroccanpixels.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Entity
@NoArgsConstructor @AllArgsConstructor @Table @Setter @Getter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean isImageExists;
    @OneToMany(mappedBy = "category")
    private Collection<Image> images;


}
