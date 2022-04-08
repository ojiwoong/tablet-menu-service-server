package com.example.orderservice.dto;

import lombok.Data;

@Data
public class OrderDto extends BaseTimeDto {
    private Long id;
    private Long userId;
    private Integer totalAmount;
}
