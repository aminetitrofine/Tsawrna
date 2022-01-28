package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.auth.IAuthenticationFacade;
import com.moroccanpixels.moroccanpixels.dto.UserResponseDto;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;
    private final IAuthenticationFacade authenticationFacade;

    @Autowired
    public UserController(UserService userService, IAuthenticationFacade authenticationFacade) {
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping
    public List<UserResponseDto> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(headers={"target=adminCreateUser"})
    public UserResponseDto adminCreateUser(@RequestBody User user){
        return userService.adminCreateUser(user);
    }
    @PostMapping(headers={"target=signup"})
    public UserResponseDto signup(@RequestBody User user){
        return userService.singup(user);
    }

}
