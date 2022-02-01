package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.dto.UpdatePasswordRequestDto;
import com.moroccanpixels.moroccanpixels.dto.UserResponseDto;
import com.moroccanpixels.moroccanpixels.mapper.EntityToDto;
import com.moroccanpixels.moroccanpixels.repository.UserRepository;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

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

    public UserResponseDto singup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return EntityToDto.userToUserResponseDto(userRepository.save(user));
    }

    @Transactional
    public void updateFirstName(Long userId, String firstName) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException(String.format("user with id %d not found",userId)));
        user.setFirstName(firstName);
    }
    @Transactional
    public void updateLastName(Long userId, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException(String.format("user with id %d not found",userId)));
        user.setLastName(name);
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
