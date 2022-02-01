package com.moroccanpixels.moroccanpixels.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.moroccanpixels.moroccanpixels.model.ImageType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SAVE",
            joinColumns = {@JoinColumn(name = "IMAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private Set<User> savedBy= new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "IMAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "KEYWORD_ID")}
    )
    private Set<Keyword> keywords =new HashSet<>();

    public String getPath() {
        return "/image/"+this.id;
    }

    public String getLocalPath() {
        return "/uploads/images/"+this.owner.getUsername()+"/"+this.id+"."+this.type.value();
    }
    public int getSaveCount() {
        return this.savedBy.size();
    }
    public void addSavedByUser(User user){
        if(this.savedBy==null) this.savedBy=new HashSet<User>();
        this.savedBy.add(user);
    }
    public void addKeyword(Keyword keyword) {
        if(this.keywords==null) this.keywords=new HashSet<Keyword>();
        this.keywords.add(keyword);
    }
}
