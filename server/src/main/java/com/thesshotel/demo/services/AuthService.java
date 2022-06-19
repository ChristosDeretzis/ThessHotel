package com.thesshotel.demo.services;

import com.thesshotel.demo.Utils.DtoModel.ModelToDto;
import com.thesshotel.demo.dtos.LoginRequest;
import com.thesshotel.demo.dtos.SignUpRequest;
import com.thesshotel.demo.dtos.AuthResponse;
import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.exceptions.AlreadyExistsException;
import com.thesshotel.demo.models.Role;
import com.thesshotel.demo.models.User;
import com.thesshotel.demo.repositories.RoleRepository;
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
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    public AuthResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        Role role = roleRepository.findByName("USER");

        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.addRole(role);

        if(userRepository.existsByEmailOrUsername(signUpRequest.getEmail(), signUpRequest.getUsername())) {
            throw new AlreadyExistsException("User already Exists");
        }

        userRepository.save(user);
        
        String accessToken = jwtUtil.generateAccessToken(user);
        UserDto userDto = ModelToDto.convertUserModelToDto(user);
        AuthResponse authResponse = new AuthResponse(userDto, accessToken);



        return authResponse;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        UserDto userDto = ModelToDto.convertUserModelToDto(user);

        String accessToken = jwtUtil.generateAccessToken(user);

        AuthResponse response = new AuthResponse(userDto, accessToken);
        return response;
    }
}
