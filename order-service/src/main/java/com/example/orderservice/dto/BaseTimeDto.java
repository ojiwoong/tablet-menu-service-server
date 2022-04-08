package com.example.orderservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

public class BaseTimeDto {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
