package com.example.orderservice.service;

import com.example.orderservice.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    /* 결제하기 */
    PaymentDto createPayment(PaymentDto paymentDto, Long userId);

    /* 결제 내역 전체 조회 */
    List<PaymentDto> getAllPayments();
}
