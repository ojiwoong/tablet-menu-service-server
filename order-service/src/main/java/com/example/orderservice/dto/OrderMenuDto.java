package com.example.orderservice.dto;

import com.example.orderservice.entity.MenuEntity;
import com.example.orderservice.entity.OrderEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderMenuDto extends BaseTimeDto{
    private Long id;
    private Long menuId;
    private Integer quantity;
    private Integer amount;

    private OrderEntity orderEntity;
    private MenuEntity menuEntity;
}
