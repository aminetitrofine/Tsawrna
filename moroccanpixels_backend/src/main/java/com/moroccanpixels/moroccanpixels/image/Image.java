package com.moroccanpixels.moroccanpixels.image;

import com.moroccanpixels.moroccanpixels.user.User;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OWNER_ID")
    private User owner;

    private String path;
    private Instant uploadedAt;
    private Instant lastModified;
    private String description;
    private Long downloadCount;
    private Long viewCount;
    private Long saveCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SAVE",
            joinColumns = {@JoinColumn(name = "IMAGE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private Set<User> savedBy;
}
