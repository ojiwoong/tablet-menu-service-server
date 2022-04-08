package com.example.orderservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_menus")
public class OrderMenuEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name="order_id")
    OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name="menu_id")
    MenuEntity menuEntity;
}
