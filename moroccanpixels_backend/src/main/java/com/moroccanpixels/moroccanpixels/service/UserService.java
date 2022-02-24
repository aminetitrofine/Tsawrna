package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.auth.AuthenticationFacade;
import com.moroccanpixels.moroccanpixels.config.ImageConfig;
import com.moroccanpixels.moroccanpixels.dto.SignUpFormDto;
import com.moroccanpixels.moroccanpixels.dto.UpdatePasswordRequestDto;
import com.moroccanpixels.moroccanpixels.dto.UserResponseDto;
import com.moroccanpixels.moroccanpixels.exceptions.InvalidEmailException;
import com.moroccanpixels.moroccanpixels.exceptions.InvalidUsernameException;
import com.moroccanpixels.moroccanpixels.mapper.EntityToDto;
import com.moroccanpixels.moroccanpixels.model.ImageType;
import com.moroccanpixels.moroccanpixels.repository.UserRepository;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;
import com.moroccanpixels.moroccanpixels.security.PasswordConfig;
import com.moroccanpixels.moroccanpixels.utils.ImageUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.moroccanpixels.moroccanpixels.model.StatusType.CONFIRMED;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;
    private final ImageConfig imageConfig;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationFacade authenticationFacade, ImageConfig imageConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationFacade = authenticationFacade;
        this.imageConfig = imageConfig;
    }

    public List<UserResponseDto> getUsers() {
        return EntityToDto.userToUserResponseDto(userRepository.findAll());
    }

    public UserResponseDto adminCreateUser(User user) {

        return EntityToDto.userToUserResponseDto(userRepository.save(user));
    }

    public UserResponseDto singUp(SignUpFormDto signUpForm) {
        userRepository.findByUsername(signUpForm.getUsername())
                .ifPresent(
                        (u)-> {
                            throw new InvalidUsernameException(String.format("username %s is already taken.",u.getUsername()));
                        });
        userRepository.findByEmail(signUpForm.getEmail())
                .ifPresent(
                        (u)-> {
                            throw new InvalidEmailException(String.format("email %s is already taken.",u.getEmail()));
                        });
        String password = signUpForm.getPassword();
        String passwordConfirmation = signUpForm.getPasswordConfirmation();
        if(!password.equals(passwordConfirmation))
            throw new IllegalStateException("password and password confirmation are not the same.");
        if(!PasswordConfig.isValid(password))
            throw new IllegalStateException("password must contain ..TODO.....");

        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(signUpForm.getFirstName());
        user.setLastName(signUpForm.getLastName());
        user.setUsername(signUpForm.getUsername());
        user.setBirthdate(signUpForm.getBirthDate());
        //TODO: add email validation
        user.setEmail(signUpForm.getEmail());
        user.setStatus(CONFIRMED);
        user.setRole(ApplicationUserRole.fromName(signUpForm.getRole()));

        return EntityToDto.userToUserResponseDto(userRepository.save(user));
    }

    @Transactional
    public void updateName(Long userId, String firstName,String lastName) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException(String.format("user with id %d not found",userId)));
        if(firstName!=null)
            user.setFirstName(firstName);
        if(lastName!=null)
            user.setLastName(lastName);
    }
    @Transactional
    public void updateEmail(Long userId, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException(String.format("user with id %d not found",userId)));
        user.setEmail(email);
    }

    @Transactional
    public void updatePassword(Long userId, UpdatePasswordRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException(String.format("user with id %d not found",userId)));
        if(!user.getPassword().equals(passwordEncoder.encode(dto.getOldPassword()))){
            throw new IllegalStateException("invalid password");
        }
        if(dto.getNewPassword().equals(dto.getNewPasswordConfirmation())){
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        }else{
            throw new IllegalStateException("Password and Password Confirmation aren't the same.");
        }
    }

    public Map<String,String> getAuthenticatedUser() {
        return Map.of(
                "username",authenticationFacade.getAuthenticatedUsername(),
                "role",authenticationFacade.getAuthenticatedUserRole()
        );
    }

    @Transactional
    public void setProfilePicture(MultipartFile profilePicture) {
        String username = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("user " + username + " not found"));
        //saving file

        String directory = String.format("%s/profile-pictures/%s/",imageConfig.getDirectory(),username);
        String fileName = String.format("%s.%s",username, Objects.requireNonNull(ImageType.fromContentType(profilePicture.getContentType())).value());
        ImageUtils.saveImage(profilePicture,directory,fileName);
        user.setProfilePictureUrl(directory+fileName);
    }

    public byte[] viewProfilePicture(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("user " + username + " not found"));
        InputStream in;
        try{
            in = new FileInputStream(user.getProfilePictureUrl());
            return IOUtils.toByteArray(in);
        }catch(IOException e1){
            try{
                in = new FileInputStream("src/main/resources/static/user.png");
                return  IOUtils.toByteArray(in);
            }catch(IOException e2){
                e2.printStackTrace();
            }
        }
        return new byte[0];
    }
}