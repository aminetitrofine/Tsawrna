package com.moroccanpixels.moroccanpixels.model.entity;

import com.moroccanpixels.moroccanpixels.model.StatusType;
import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="MOROCCAN_PIXELS_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Unique
    private String username;

    private String email;
    private LocalDate birthdate;
    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole role;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    //private Plan plan;
    @OneToMany(mappedBy="owner",fetch=FetchType.LAZY)
    private Set<Image> images=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SAVE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID")}
    )
    private Set<Image> savedImages=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "VIEW",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID")}
    )
    private Set<Image> viewedImages=new HashSet<>();

    @OneToOne
    private Subscription subscription;

    public User(String username, String email,String firstName,String lastName, String password, ApplicationUserRole role, StatusType status) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    public void addSavedImage(Image image) {
        this.savedImages.add(image);
    }
    public void addViewedImage(Image image) {
        this.viewedImages.add(image);
    }
    public void removeSavedImage(Image image) {
        this.savedImages.remove(image);
    }
}
