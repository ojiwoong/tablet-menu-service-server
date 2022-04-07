package com.example.menuservice.vo;

import lombok.Data;

@Data
public class RequestMenu {
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;
    private String description;
    private String imageUrl;
}
