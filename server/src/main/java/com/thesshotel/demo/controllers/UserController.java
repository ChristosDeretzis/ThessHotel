package com.thesshotel.demo.controllers;

import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.models.User;
import com.thesshotel.demo.services.UserService;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
@Api( value = "User Related APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{id}")
    @ApiOperation(value = "This method is update the current user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully User updated"),
            @ApiResponse(code = 401, message = "Full authentication is required to access this resource"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public UserDto updateUser(@RequestBody UserDto user, @ApiParam(name = "id", type = "string") @PathVariable("id") Integer id) {
        return userService.updateUser(user, id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "This method is get the current user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully User retrieved"),
            @ApiResponse(code = 401, message = "Full authentication is required to access this resource"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public UserDto getUser(@ApiParam(name = "id", type = "string") @PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "This method is delete the current user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully User Deleted"),
            @ApiResponse(code = 401, message = "Full authentication is required to access this resource"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public UserDto deleteUser(@ApiParam(name = "id", type = "string") @PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }
}
