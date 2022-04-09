package com.example.orderservice.controller;

import com.example.orderservice.dto.PaymentDto;
import com.example.orderservice.entity.PaymentMethodEntity;
import com.example.orderservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /* 결제하기 */
    @PostMapping(value = "/payments/{userId}")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto, @PathVariable(name = "userId") Long userId){

        PaymentDto createdPaymentDto = paymentService.createPayment(paymentDto, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPaymentDto);
    }

    /* 결제 내역 전체 조회 */
    @GetMapping(value = "/payments")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {

        List<PaymentDto> paymentDtoList = paymentService.getAllPayments();

        return ResponseEntity.status(HttpStatus.OK).body(paymentDtoList);
    }
}
