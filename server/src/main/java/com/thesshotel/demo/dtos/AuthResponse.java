package com.thesshotel.demo.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class AuthResponse {

    private UserDto user;

    @ApiModelProperty(notes = "The access Token")
    private String accessToken;
}
