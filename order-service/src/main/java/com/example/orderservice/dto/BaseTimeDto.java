package com.example.orderservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseTimeDto {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
