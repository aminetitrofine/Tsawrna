package com.moroccanpixels.moroccanpixels.User;

import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;

import javax.persistence.*;

@Entity
@Table(name="MOROCCAN_PIXELS_USER")
public class User {

    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName="user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="user_sequence"
    )
    private Long id;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole role;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    //private Plan plan;


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
}
