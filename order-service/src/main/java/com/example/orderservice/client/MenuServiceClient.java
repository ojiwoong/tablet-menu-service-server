package com.example.orderservice.client;

import com.example.orderservice.dto.MenuDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("menu-service")
public interface MenuServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/menus")
    List<MenuDto> getAllMenus();

    @RequestMapping(method = RequestMethod.GET, value = "/menus/{id}")
    MenuDto getMenu(@PathVariable Long id);
}
