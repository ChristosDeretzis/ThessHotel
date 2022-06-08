package com.thesshotel.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesshotel.demo.Utils.DtoModel.ModelToDto;
import com.thesshotel.demo.controllers.AuthController;
import com.thesshotel.demo.dtos.LoginRequest;
import com.thesshotel.demo.dtos.SignUpRequest;
import com.thesshotel.demo.dtos.AuthResponse;
import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.exceptions.AlreadyExistsException;
import com.thesshotel.demo.models.User;
import com.thesshotel.demo.repositories.UserRepository;
import com.thesshotel.demo.security.JwtTokenUtil;
import com.thesshotel.demo.services.AuthService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserRepository userRepository;

    @MockBean
    AuthService authService;

    @MockBean
    JwtTokenUtil jwtTokenUtil;

    private static User user;
    private static UserDto userDto;
    private static SignUpRequest signUpRequest;
    private static LoginRequest loginRequest;

    @BeforeAll
    public static void setUp() {
        user = new User().builder()
                .email("deretzischris@gmail.com")
                .username("chris")
                .password("Yah6tvh*98")
                .build();

        userDto = ModelToDto.convertUserModelToDto(user);

        signUpRequest = SignUpRequest.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        loginRequest = LoginRequest.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Test
    public void signupUser_success() throws Exception {
        String token = jwtTokenUtil.generateAccessToken(user);

        AuthResponse authResponse = new AuthResponse().builder()
                .user(userDto)
                .accessToken(token)
                .build();

        Mockito.when(authService.signUp(signUpRequest)).thenReturn(authResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(signUpRequest))
                .with(csrf())
                .with(SecurityMockMvcRequestPostProcessors.user("chris"));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.user.email", Matchers.is(user.getEmail())))
                .andExpect(jsonPath("$.user.username", Matchers.is(user.getUsername())))
                .andExpect(jsonPath("$.accessToken", Matchers.is(token)));
    }

    @Test
    public void signupUser_AlreadyExistsFailure() throws Exception {
        Mockito.when(authService.signUp(signUpRequest)).thenThrow(new AlreadyExistsException("User already exists"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(signUpRequest))
                .with(csrf())
                .with(SecurityMockMvcRequestPostProcessors.user("chris"));

        mockMvc.perform(mockRequest)
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message", Matchers.is("User already exists")));
    }

    @Test
    public void loginUser_success() throws Exception {
        String token = jwtTokenUtil.generateAccessToken(user);

        AuthResponse authResponse = new AuthResponse().builder()
                .user(userDto)
                .accessToken(token)
                .build();

        Mockito.when(authService.login(loginRequest)).thenReturn(authResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(loginRequest))
                .with(csrf())
                .with(SecurityMockMvcRequestPostProcessors.user("chris"));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.user.email", Matchers.is(user.getEmail())))
                .andExpect(jsonPath("$.user.username", Matchers.is(user.getUsername())))
                .andExpect(jsonPath("$.accessToken", Matchers.is(token)));
    }

    @Test
    public void loginUser_BadCredentialsFailure() throws Exception {
        Mockito.when(authService.login(loginRequest)).thenThrow(new BadCredentialsException("Bad Credentials"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(loginRequest))
                .with(csrf())
                .with(SecurityMockMvcRequestPostProcessors.user("chris"));

        mockMvc.perform(mockRequest)
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message", Matchers.is("Bad Credentials")));
    }
}
