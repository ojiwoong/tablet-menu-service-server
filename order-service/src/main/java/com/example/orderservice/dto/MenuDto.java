package com.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto extends BaseTimeDto {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
}
