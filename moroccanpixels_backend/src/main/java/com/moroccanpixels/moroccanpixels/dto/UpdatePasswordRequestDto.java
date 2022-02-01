package com.moroccanpixels.moroccanpixels.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequestDto {
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirmation;
}
