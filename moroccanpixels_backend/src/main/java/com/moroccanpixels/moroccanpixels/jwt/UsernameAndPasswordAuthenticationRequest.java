package com.moroccanpixels.moroccanpixels.jwt;

import lombok.*;

@Data
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
