package com.example.orderservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer totalAmount;

    @ManyToOne
    @JoinColumn(name="payment_method_id")
    private PaymentMethodEntity paymentMethodEntity;
}
