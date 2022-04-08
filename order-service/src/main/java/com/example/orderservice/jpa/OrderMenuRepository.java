package com.example.orderservice.jpa;

import com.example.orderservice.entity.OrderMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenuEntity, Long> {
}
