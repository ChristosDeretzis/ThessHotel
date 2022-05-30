package com.thesshotel.demo.controllers;

import com.thesshotel.demo.dtos.*;
import com.thesshotel.demo.exceptions.AlreadyExistsException;
import com.thesshotel.demo.services.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController()
@RequestMapping("auth")
@Api( value = "Authentication Related APIs")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value = "This method is used to register a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully User Created"),
            @ApiResponse(code = 422, message = "User already exists"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/signup")
    private AuthResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return authService.signUp(signUpRequest);
    }

    @ApiOperation(value = "This method is used to login a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully User logged in"),
            @ApiResponse(code = 400, message = "User has bad credentials"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/login")
    private AuthResponse login(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        try {
            return authService.login(loginRequest);
        } catch (BadCredentialsException ex) {
            throw new Exception("Bad Credentials");
        }
    }
}
