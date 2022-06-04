package com.thesshotel.demo.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesshotel.demo.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuthException implements AuthenticationEntryPoint {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(authException.getMessage());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setTimestamp(LocalDateTime.now().toString());

        // setting the response HTTP status code
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // serializing the response body in JSON
        response
                .getOutputStream()
                .println(
                        objectMapper.writeValueAsString(errorResponse)
                );
    }
}
