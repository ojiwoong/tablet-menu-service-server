package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="order_id")
    OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name="menu_id")
    MenuEntity menuEntity;
}
