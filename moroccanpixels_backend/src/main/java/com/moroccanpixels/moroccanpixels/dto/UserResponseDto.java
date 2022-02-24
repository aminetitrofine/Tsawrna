package com.moroccanpixels.moroccanpixels.dto;

import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;
import com.moroccanpixels.moroccanpixels.model.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private LocalDate birthdate;
    private String role;
    private String status;
    private Set<String> images; //Set of image's paths
    private Set<String> savedImages; //Set of saved image's paths
}
