package com.thesshotel.demo.services;

import com.thesshotel.demo.dtos.LoginRequest;
import com.thesshotel.demo.dtos.SignUpRequest;
import com.thesshotel.demo.dtos.AuthResponse;
import com.thesshotel.demo.exceptions.AlreadyExistsException;
import com.thesshotel.demo.models.User;
import com.thesshotel.demo.repositories.UserRepository;
import com.thesshotel.demo.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    public AuthResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        if(userRepository.existsByEmailOrUsername(signUpRequest.getEmail(), signUpRequest.getUsername())) {
            throw new AlreadyExistsException("User already Exists");
        }

        String accessToken = jwtUtil.generateAccessToken(user);
        AuthResponse authResponse = new AuthResponse(user.getEmail(), user.getUsername(), accessToken);

        userRepository.save(user);

        return authResponse;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(user);

        AuthResponse response = new AuthResponse(user.getEmail(), user.getUsername(), accessToken);
        return response;
    }
}
