package com.example.orderservice.vo;

import lombok.Data;

@Data
public class RequestOrderMenu {
    private Long menuId;
    private Integer quantity;
    private Integer amount;
}
