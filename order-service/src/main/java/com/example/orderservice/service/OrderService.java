package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderMenuDto;
import com.example.orderservice.vo.RequestOrder;

import java.util.List;

public interface OrderService {
    /* 주문하기 */
    OrderDto createOrder(OrderDto orderDto, List<OrderMenuDto> orderMenuDtoList);


}
