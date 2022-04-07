package com.example.menuservice.jpa;

import com.example.menuservice.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
}
