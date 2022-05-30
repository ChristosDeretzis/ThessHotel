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

    @ApiModelProperty(notes = "The email of the user", example = "example@gmail.com")
    private String email;

    @ApiModelProperty(notes = "The username of the user", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJudWxsLGV4YW1wbGVAZ21haWwuY29tIiwiaXNzIjoiQ29kZUphdmEiLCJpYXQiOjE2NTM0ODAxNzAsImV4cCI6MTY1MzU2NjU3MH0.u29j_1v2xxhtpEaf-vEq-aPI_UHm3vQZMlG0jpAd3uThQ0vK-VxraQQDgITXhtvWTIhN59vBCUfEV1fsTLvMQw")
    private String username;

    @ApiModelProperty(notes = "The access Token")
    private String accessToken;
}
