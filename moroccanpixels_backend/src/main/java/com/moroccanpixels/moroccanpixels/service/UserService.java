package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.dto.UserResponseDto;
import com.moroccanpixels.moroccanpixels.mapper.EntityToDto;
import com.moroccanpixels.moroccanpixels.repository.UserRepository;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getUsers() {
        return EntityToDto.userToUserResponseDto(userRepository.findAll());
    }

    public UserResponseDto adminCreateUser(User user) {

        return EntityToDto.userToUserResponseDto(userRepository.save(user));
    }

    public UserResponseDto singup(User user) {
        return EntityToDto.userToUserResponseDto(userRepository.save(user));
    }
}
