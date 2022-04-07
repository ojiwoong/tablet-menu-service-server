package com.example.menuservice.dto;

import lombok.Data;

@Data
public class MenuDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;
    private String description;
    private String imageUrl;
}
