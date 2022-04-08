package com.example.menuservice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestMenu {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
}
