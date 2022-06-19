package com.thesshotel.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesshotel.demo.Utils.Date.DateParser;
import com.thesshotel.demo.Utils.DtoModel.ModelToDto;
import com.thesshotel.demo.config.SpringSecurityWebAuxTestConfig;
import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.exceptions.NotFoundException;
import com.thesshotel.demo.models.Role;
import com.thesshotel.demo.models.User;
import com.thesshotel.demo.repositories.UserRepository;
import com.thesshotel.demo.security.JwtTokenUtil;
import com.thesshotel.demo.services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    private User user;
    private UserDto userDto, newUserDto;
    private Set<Role> roles = new HashSet<>();

    @BeforeEach
    public void setUp() {
        roles.add(new Role(1, "USER"));
        roles.add(new Role(2, "HOTEL_OWNER"));

        user = new User().builder()
                .id(1)
                .username("chris99")
                .email("deretzischris@gmail.com")
                .password("Ytgdhef76&8")
                .firstName("Nikos")
                .lastName("Papadopoulos")
                .dateOfBirth(DateParser.parseDate("24/09/1994"))
                .country("Greece")
                .state("Central Macedonia")
                .city("Thessaloniki")
                .strAddress("Egnatias")
                .strNumber(34)
                .zipCode(54248)
                .roles(roles)
                .build();

        userDto = ModelToDto.convertUserModelToDto(user);

        newUserDto = ModelToDto.convertUserModelToDto(user);
        newUserDto.setCity("Athens");
        newUserDto.setState("Attiki");
        newUserDto.setStrAddress("Katechaki");
        newUserDto.setStrNumber(45);
        newUserDto.setZipCode(45364);
    }

    @Test
    public void getUserById_success() throws Exception {
        Mockito.when(userService.getUser(user.getId())).thenReturn(userDto);

        String accessToken = jwtTokenUtil.generateAccessToken(user);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/1")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.email", Matchers.is("deretzischris@gmail.com")))
                .andExpect(jsonPath("$.username", Matchers.is("chris99")))
                .andExpect(jsonPath("$.firstName", Matchers.is("Nikos")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Papadopoulos")))
                .andExpect(jsonPath("$.dateOfBirth", Matchers.is("1994-09-23T21:00:00.000+00:00")))
                .andExpect(jsonPath("$.country", Matchers.is("Greece")))
                .andExpect(jsonPath("$.city", Matchers.is("Thessaloniki")))
                .andExpect(jsonPath("$.state", Matchers.is("Central Macedonia")))
                .andExpect(jsonPath("$.strAddress", Matchers.is("Egnatias")))
                .andExpect(jsonPath("$.strNumber", Matchers.is(34)))
                .andExpect(jsonPath("$.zipCode", Matchers.is(54248)))
                .andExpect(jsonPath("$.roles[*]", Matchers.containsInAnyOrder("USER", "HOTEL_OWNER")));
    }

    @Test
    public void getUserById_unauthorized() throws Exception {
        Mockito.when(userService.getUser(user.getId())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message", Matchers.is("Full authentication is required to access this resource")));
    }

    @Test
    public void getUserById_notFound() throws Exception {
        Mockito.when(userService.getUser(2)).thenThrow(new NotFoundException("User was not found"));

        String accessToken = jwtTokenUtil.generateAccessToken(user);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/2")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", Matchers.is("User was not found")));
    }

    @Test
    public void updateUserById_success() throws Exception {
        Mockito.when(userService.updateUser(newUserDto, user.getId())).thenReturn(newUserDto);

        String accessToken = jwtTokenUtil.generateAccessToken(user);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/user/1")
                .header("Authorization", "Bearer " + accessToken)
                .content(this.mapper.writeValueAsString(newUserDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.email", Matchers.is("deretzischris@gmail.com")))
                .andExpect(jsonPath("$.username", Matchers.is("chris99")))
                .andExpect(jsonPath("$.firstName", Matchers.is("Nikos")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Papadopoulos")))
                .andExpect(jsonPath("$.dateOfBirth", Matchers.is("1994-09-23T21:00:00.000+00:00")))
                .andExpect(jsonPath("$.country", Matchers.is("Greece")))
                .andExpect(jsonPath("$.city", Matchers.is("Athens")))
                .andExpect(jsonPath("$.state", Matchers.is("Attiki")))
                .andExpect(jsonPath("$.strAddress", Matchers.is("Katechaki")))
                .andExpect(jsonPath("$.strNumber", Matchers.is(45)))
                .andExpect(jsonPath("$.zipCode", Matchers.is(45364)))
                .andExpect(jsonPath("$.roles[*]", Matchers.containsInAnyOrder("USER", "HOTEL_OWNER")));
    }

    @Test
    public void updateUserById_unauthorized() throws Exception {
        Mockito.when(userService.updateUser(newUserDto, user.getId())).thenReturn(newUserDto);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/user/1")
                .content(this.mapper.writeValueAsString(newUserDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message", Matchers.is("Full authentication is required to access this resource")));
    }

    @Test
    public void updateUserById_notFound() throws Exception {
        Mockito.when(userService.updateUser(newUserDto, 2)).thenThrow(new NotFoundException("User was not found"));

        String accessToken = jwtTokenUtil.generateAccessToken(user);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/user/2")
                .header("Authorization", "Bearer " + accessToken)
                .content(this.mapper.writeValueAsString(newUserDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", Matchers.is("User was not found")));
    }

    @Test
    public void deleteUserById_success() throws Exception {
        Mockito.when(userService.deleteUser(user.getId())).thenReturn(newUserDto);

        String accessToken = jwtTokenUtil.generateAccessToken(user);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/user/1")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.email", Matchers.is("deretzischris@gmail.com")))
                .andExpect(jsonPath("$.username", Matchers.is("chris99")))
                .andExpect(jsonPath("$.firstName", Matchers.is("Nikos")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Papadopoulos")))
                .andExpect(jsonPath("$.dateOfBirth", Matchers.is("1994-09-23T21:00:00.000+00:00")))
                .andExpect(jsonPath("$.country", Matchers.is("Greece")))
                .andExpect(jsonPath("$.city", Matchers.is("Athens")))
                .andExpect(jsonPath("$.state", Matchers.is("Attiki")))
                .andExpect(jsonPath("$.strAddress", Matchers.is("Katechaki")))
                .andExpect(jsonPath("$.strNumber", Matchers.is(45)))
                .andExpect(jsonPath("$.zipCode", Matchers.is(45364)))
                .andExpect(jsonPath("$.roles[*]", Matchers.containsInAnyOrder("USER", "HOTEL_OWNER")));
    }

    @Test
    public void deleteUserById_unauthorized() throws Exception {
        Mockito.when(userService.deleteUser(user.getId())).thenReturn(newUserDto);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message", Matchers.is("Full authentication is required to access this resource")));
    }

    @Test
    public void deleteUserById_notFound() throws Exception {
        Mockito.when(userService.deleteUser(2)).thenThrow(new NotFoundException("User was not found"));

        String accessToken = jwtTokenUtil.generateAccessToken(user);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/user/2")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", Matchers.is("User was not found")));
    }
}
