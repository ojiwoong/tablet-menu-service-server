package com.example.orderservice.jpa;

import com.example.orderservice.entity.OrderMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenuEntity, Long> {
}
