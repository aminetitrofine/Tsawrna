package com.moroccanpixels.moroccanpixels.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="MOROCCAN_PIXELS_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private LocalDate birthdate;



    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole role;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    //private Plan plan;
    @JsonManagedReference
    @OneToMany(mappedBy="owner",fetch=FetchType.LAZY)
    private Set<Image> images;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SAVE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID")}
    )
    private Set<Image> savedImages;

    public User() {
    }

    public User(String username, String email, String password, ApplicationUserRole role, StatusType status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ApplicationUserRole getRole() {
        return role;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(ApplicationUserRole role) {
        this.role = role;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Set<Image> getSavedImages() {
        return savedImages;
    }

    public void setSavedImages(Set<Image> savedImages) {
        this.savedImages = savedImages;
    }
    public void addSavedImage(Image image) {
        if(this.savedImages==null) this.savedImages = new HashSet<Image>();
        this.savedImages.add(image);
    }
    public void removeSavedImage(Image image) {
        if(this.savedImages==null) return;
        this.savedImages.remove(image);
    }
}
