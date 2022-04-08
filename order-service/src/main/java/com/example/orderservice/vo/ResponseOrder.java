package com.example.orderservice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseOrder {
    private Long id;
    private Long userId;
    private Integer totalAmount;
}
