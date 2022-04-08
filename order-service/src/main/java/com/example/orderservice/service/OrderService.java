package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderMenuDto;
import com.example.orderservice.vo.RequestOrder;

import java.util.List;

public interface OrderService {
    /* 주문하기 */
    OrderDto createOrder(OrderDto orderDto, List<OrderMenuDto> orderMenuDtoList);

    /* 특정 사용자 주문삭제 */
    void deleteOrderByUserId(Long userId);

    /* 특정 사용자 주문 조회 */
    List<OrderDto> getOrderByUserId(Long userId);
}
