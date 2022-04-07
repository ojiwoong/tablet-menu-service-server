package com.example.menuservice.service;

import com.example.menuservice.dto.MenuDto;

import java.util.List;

public interface MenuService {
    /* 전체 메뉴 조회 */
    List<MenuDto> getAllMenus();
}
