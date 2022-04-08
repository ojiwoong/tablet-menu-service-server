package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderMenuDto;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.entity.OrderMenuEntity;
import com.example.orderservice.jpa.OrderMenuRepository;
import com.example.orderservice.jpa.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    OrderRepository orderRepository;
    OrderMenuRepository orderMenuRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMenuRepository orderMenuRepository) {
        this.orderRepository = orderRepository;
        this.orderMenuRepository = orderMenuRepository;
    }

    @Transactional
    @Override
    public OrderDto createOrder(OrderDto orderDto, List<OrderMenuDto> orderMenuDtoList) {
        OrderEntity orderEntity = new ModelMapper().map(orderDto, OrderEntity.class);

        // 주문 save
        OrderEntity createdOrder = orderRepository.save(orderEntity);

        List<OrderMenuEntity> orderMenuEntityList = new ArrayList<>();

        // 주문 정보 주문 메뉴에 매핑
        orderMenuDtoList.forEach(orderMenuDto -> {
            orderMenuDto.setOrderEntity(createdOrder);
            orderMenuEntityList.add(new ModelMapper().map(orderMenuDto, OrderMenuEntity.class));
        });

        // 주문 메뉴 save
        orderMenuRepository.saveAll(orderMenuEntityList);

        OrderDto createdOrderDto = new ModelMapper().map(createdOrder, OrderDto.class);

        return createdOrderDto;
    }
}
