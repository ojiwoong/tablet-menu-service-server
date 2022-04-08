package com.example.orderservice.service;

import com.example.orderservice.dto.PaymentDto;
import com.example.orderservice.entity.PaymentEntity;
import com.example.orderservice.entity.PaymentMethodEntity;
import com.example.orderservice.jpa.PaymentMethodRepository;
import com.example.orderservice.jpa.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    PaymentRepository paymentRepository;
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMethodRepository paymentMethodRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Transactional
    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {

        PaymentEntity paymentEntity = new ModelMapper().map(paymentDto, PaymentEntity.class);

        // 결제된 paymentMethodId 값으로 PaymentMethodEntity 조회
        PaymentMethodEntity paymentMethodEntity = paymentMethodRepository.findById(paymentDto.getPaymentMethodId()).orElse(null);

        // 조회된 PaymentMethodEntity paymentEntity Set
        paymentEntity.setPaymentMethodEntity(paymentMethodEntity);

        // 결제 save
        PaymentEntity createdPaymentEntity = paymentRepository.save(paymentEntity);

        PaymentDto createdPaymentDto = new ModelMapper().map(createdPaymentEntity, PaymentDto.class);

        return createdPaymentDto;
    }

    @Override
    public List<PaymentDto> getAllPayments() {

        // 전체 결제 내역 find
        List<PaymentEntity> paymentEntityList = paymentRepository.findAll();

        List<PaymentDto> paymentDtoList = new ArrayList<>();

        paymentEntityList.forEach(paymentEntity -> {
            paymentDtoList.add(new ModelMapper().map(paymentEntity, PaymentDto.class));
        });

        return paymentDtoList;
    }
}
