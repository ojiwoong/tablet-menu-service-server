package com.example.orderservice.vo;

import lombok.Data;

@Data
public class ResponseOrder {
    private Long id;
    private Long userId;
    private Integer totalAmount;
}
