package com.thesshotel.demo.config;

import com.thesshotel.demo.models.User;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = User.builder().username("chris99").email("deretzischris@gmail.com").password("Ytgdhef76&8").build();

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicUser
        ));
    }
}
