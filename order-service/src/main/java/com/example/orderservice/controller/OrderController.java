package com.example.orderservice.controller;

import com.example.orderservice.client.MenuServiceClient;
import com.example.orderservice.dto.MenuDto;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderMenuDto;
import com.example.orderservice.entity.MenuEntity;
import com.example.orderservice.entity.OrderMenuEntity;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrderMenu;
import com.example.orderservice.vo.ResponseOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/")
public class OrderController {
    OrderService orderService;
    MenuServiceClient menuClient;

    @Autowired
    public OrderController(OrderService orderService, MenuServiceClient menuClient) {
        this.orderService = orderService;
        this.menuClient = menuClient;
    }

    /* 주문하기 */
    @PostMapping(value = "/orders/{userId}")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") Long userId, @RequestBody Map<String, Object> param) {
        // ModelMapper 객체 생성 및 셋팅
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // 주문 데이터 DTO에 담기
        OrderDto orderDto = modelMapper.map(param.get("orders"), OrderDto.class);
        orderDto.setUserId(userId);

        // 주문관리 데이터 DTO 리스트에 담기
        ObjectMapper mapper = new ObjectMapper();

        List<OrderMenuDto> orderMenuDtoList = new ArrayList<>();
        List<RequestOrderMenu> requestOrderMenuList = mapper.convertValue(
                param.get("order_menus"),
                new TypeReference<List<RequestOrderMenu>>(){}
        );

        requestOrderMenuList.forEach(requestOrderMenu -> {
            OrderMenuDto orderMenuDto = modelMapper.map(requestOrderMenu, OrderMenuDto.class);

            // Fegin client 이용해서 menuClient 에서 메뉴 정보 가져오기
            MenuDto menuDto = menuClient.getMenu(requestOrderMenu.getMenuId());

            orderMenuDto.setMenuEntity(modelMapper.map(menuDto, MenuEntity.class));
            orderMenuDtoList.add(orderMenuDto);
        });

        OrderDto createdDto = orderService.createOrder(orderDto, orderMenuDtoList);

        ResponseOrder responseOrder = modelMapper.map(createdDto, ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseOrder);
    }
    
    /* 주문삭제 */
    @DeleteMapping(value = "/orders/{userId}")
    public ResponseEntity deleteOrder(@PathVariable("userId") Long userId) {



        return ResponseEntity.status(HttpStatus.OK).body("주문삭제 완료");
    }
}
