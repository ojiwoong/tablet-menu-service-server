package com.example.menuservice.controller;

import com.example.menuservice.dto.MenuDto;
import com.example.menuservice.service.MenuService;
import com.example.menuservice.vo.ResponseMenu;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 전체 메뉴 조회 */
    @GetMapping("/menus")
    public ResponseEntity<List<ResponseMenu>> getAllMenus() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<MenuDto> menuDtoList = menuService.getAllMenus();

        List<ResponseMenu> responseMenuList = new ArrayList<>();

        menuDtoList.forEach(menuDto -> {
            responseMenuList.add(new ModelMapper().map(menuDto, ResponseMenu.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responseMenuList);
    }

    /* 메뉴 id 검색 */
    @GetMapping("/menus/{id}")
    public ResponseEntity<ResponseMenu> getMenu(@PathVariable(name = "id") Long id) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MenuDto menuDto = menuService.getMenu(id);

        ResponseMenu responseMenu = new ModelMapper().map(menuDto, ResponseMenu.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseMenu);
    }
}
