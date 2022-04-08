package com.example.orderservice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOrderMenu {
    private Long menuId;
    private Integer quantity;
    private Integer amount;
}
