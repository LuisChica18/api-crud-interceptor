package com.example.apicrudinterceptor.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ErrorDto {
    private String code;
    private String message;
}
