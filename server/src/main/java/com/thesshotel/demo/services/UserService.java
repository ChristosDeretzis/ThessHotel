package com.thesshotel.demo.services;

import com.thesshotel.demo.dtos.LoginRequest;
import com.thesshotel.demo.dtos.LoginResponse;
import com.thesshotel.demo.dtos.SignUpRequest;
import com.thesshotel.demo.dtos.SignUpResponse;
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
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Optional<User> checkedUser = userRepository.findByEmail(signUpRequest.getEmail());

        if(checkedUser.isPresent()) {
            throw new AlreadyExistsException("User already Exists");
        }

        String accessToken = jwtUtil.generateAccessToken(user);
        SignUpResponse signUpResponse = new SignUpResponse(user.getEmail(), user.getUsername(), accessToken);

        userRepository.save(user);

        return signUpResponse;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(user);

        LoginResponse response = new LoginResponse(user.getEmail(), accessToken);
        return response;
    }
}
