package com.example.menuservice.service;

import com.example.menuservice.dto.MenuDto;
import com.example.menuservice.entity.MenuEntity;
import com.example.menuservice.jpa.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{
    MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuDto> getAllMenus() {
        List<MenuEntity> menuEntityList = menuRepository.findAll();

        List<MenuDto> menuDtoList = new ArrayList<>();

        menuEntityList.forEach(v -> {
            menuDtoList.add(new ModelMapper().map(v, MenuDto.class));
        });

        return menuDtoList;
    }

    @Override
    public MenuDto getMenu(Long id) {
        MenuEntity menuEntity = menuRepository.findById(id).orElse(null);

        MenuDto menuDto = new ModelMapper().map(menuEntity, MenuDto.class);

        return menuDto;
    }
}
