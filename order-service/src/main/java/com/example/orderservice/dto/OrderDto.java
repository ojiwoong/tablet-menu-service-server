package com.example.orderservice.dto;

import com.example.orderservice.entity.OrderMenuEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderDto extends BaseTimeDto {
    private Long id;
    private Long userId;
    private Integer totalAmount;

    private List<OrderMenuEntity> orderMenuEntity;
}
