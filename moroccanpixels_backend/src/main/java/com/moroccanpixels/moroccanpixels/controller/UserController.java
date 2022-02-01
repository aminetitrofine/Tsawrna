package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.auth.IAuthenticationFacade;
import com.moroccanpixels.moroccanpixels.dto.SignUpFormDto;
import com.moroccanpixels.moroccanpixels.dto.UpdatePasswordRequestDto;
import com.moroccanpixels.moroccanpixels.dto.UserResponseDto;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, IAuthenticationFacade authenticationFacade) {
        this.userService = userService;
    }

    @GetMapping(path ="user")
    public List<UserResponseDto> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(path = {"user"},headers={"target=adminCreateUser"})
    public UserResponseDto adminCreateUser(@RequestBody User user){
        return userService.adminCreateUser(user);
    }

    @PostMapping(path = {"signup"})
    public UserResponseDto signUp(@RequestBody SignUpFormDto signupForm){
        return userService.singUp(signupForm);
    }

    @RequestMapping(method = RequestMethod.PUT,path="user/{userId}",headers={"target=updateName"})
    public void updateName(@PathVariable Long userId,
                           @RequestBody(required = false) String firstName,
                           @RequestBody(required = false) String lastName) {
        userService.updateName(userId, firstName,lastName);
    }

    @RequestMapping(method = RequestMethod.PUT, path="user/{userId}",headers={"target=updateEmail"})
    public void updateEmail(@PathVariable Long userId,@RequestBody String email){
        userService.updateEmail(userId, email);
    }
    @RequestMapping(method = RequestMethod.PUT, path="user/{userId}",headers={"target=updatePassword"})
    public void updatePassword(@PathVariable Long userId,@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto){
        userService.updatePassword(userId, updatePasswordRequestDto);
    }

}