package com.thesshotel.demo.controllers;

import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.models.User;
import com.thesshotel.demo.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("user")
@Api( value = "User Related APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto user) {
        return userService.updateUser(user);
    }

    @GetMapping
    public UserDto getUser() {
        return userService.getUser();
    }

    @DeleteMapping
    public UserDto deleteUser() {
        return userService.deleteUser();
    }
}
