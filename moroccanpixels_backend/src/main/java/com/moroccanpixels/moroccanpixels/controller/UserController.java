package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.auth.IAuthenticationFacade;
import com.moroccanpixels.moroccanpixels.dto.UpdatePasswordRequestDto;
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

    @Autowired
    public UserController(UserService userService, IAuthenticationFacade authenticationFacade) {
        this.userService = userService;
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

    @RequestMapping(method = RequestMethod.PUT,path="{userId}",headers={"target=updateName"})
    public void updateName(@PathVariable Long userId,@RequestBody String name){
        userService.updateName(userId, name);
    }

    @RequestMapping(method = RequestMethod.PUT, path="{userId}",headers={"target=updateEmail"})
    public void updateEmail(@PathVariable Long userId,@RequestBody String email){
        userService.updateEmail(userId, email);
    }
    @RequestMapping(method = RequestMethod.PUT, path="{userId}",headers={"target=updatePassword"})
    public void updatePassword(@PathVariable Long userId,@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto){
        userService.updatePassword(userId, updatePasswordRequestDto);
    }

}
