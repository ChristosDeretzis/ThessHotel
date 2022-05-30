package com.thesshotel.demo.dtos;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
}
