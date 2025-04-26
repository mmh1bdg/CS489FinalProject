package com.radix.usermanagement.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StandardResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
}