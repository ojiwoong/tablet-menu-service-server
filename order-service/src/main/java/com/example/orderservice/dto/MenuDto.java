package com.example.orderservice.dto;

import lombok.Data;

@Data
public class MenuDto {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
}
