package com.example.orderservice.dto;

import com.example.orderservice.entity.PaymentMethodEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto extends BaseTimeDto{
    private Long id;
    private Long paymentMethodId;
    private Integer totalAmount;

    private PaymentMethodEntity paymentMethodEntity;
}
