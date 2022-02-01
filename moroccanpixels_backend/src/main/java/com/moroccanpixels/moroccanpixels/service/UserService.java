package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.dto.SignUpFormDto;
import com.moroccanpixels.moroccanpixels.dto.UpdatePasswordRequestDto;
import com.moroccanpixels.moroccanpixels.dto.UserResponseDto;
import com.moroccanpixels.moroccanpixels.mapper.EntityToDto;
import com.moroccanpixels.moroccanpixels.repository.UserRepository;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;
import com.moroccanpixels.moroccanpixels.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

import static com.moroccanpixels.moroccanpixels.model.StatusType.CONFIRMED;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
                            throw new IllegalStateException(String.format("username %s is already taken.",u.getUsername()));
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
        user.setRole(ApplicationUserRole.fromValue(signUpForm.getRole()));

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
}