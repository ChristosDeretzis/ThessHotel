package com.thesshotel.demo.controllers;

import com.thesshotel.demo.dtos.*;
import com.thesshotel.demo.exceptions.AlreadyExistsException;
import com.thesshotel.demo.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Api( tags = "Users")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "This method is used to register a user")
    @PostMapping("/signup")
    private ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        try {
            SignUpResponse signUpResponse = userService.signUp(signUpRequest);
            return ResponseEntity.ok().body(signUpResponse);
        }  catch (AlreadyExistsException e) {
            throw new AlreadyExistsException("User already exists");
        }
    }

    @ApiOperation(value = "This method is used to login")
    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        try {
            LoginResponse loginResponse = userService.login(loginRequest);
            return ResponseEntity.ok().body(loginResponse);
        } catch (BadCredentialsException ex) {
            throw new Exception("Bad Credentials");
        }
    }
}
