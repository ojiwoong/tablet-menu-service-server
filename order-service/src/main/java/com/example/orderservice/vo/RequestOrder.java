package com.example.orderservice.vo;

import com.example.orderservice.dto.OrderMenuDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestOrder {
    private Integer totalAmount;
    private List<RequestOrderMenu> orderMenuList;
}
