package com.moroccanpixels.moroccanpixels.dto;

import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;
import com.moroccanpixels.moroccanpixels.entity.StatusType;

import java.time.LocalDate;
import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private LocalDate birthdate;
    private ApplicationUserRole role;
    private StatusType status;
    private Set<String> images; //Set of image's paths
    private Set<String> savedImages; //Set of saved image's paths

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public ApplicationUserRole getRole() {
        return role;
    }

    public void setRole(ApplicationUserRole role) {
        this.role = role;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public Set<String> getSavedImages() {
        return savedImages;
    }

    public void setSavedImages(Set<String> savedImages) {
        this.savedImages = savedImages;
    }
}
