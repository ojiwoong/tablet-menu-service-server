package com.example.orderservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "menus")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column
    private String description;

    @Column
    private String imageUrl;
}
