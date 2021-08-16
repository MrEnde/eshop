package com.shop.eshop.controllers;

import com.shop.eshop.dto.OrderDto;
import com.shop.eshop.exception.InvalidInputDataException;
import com.shop.eshop.mappers.OrderMapper;
import com.shop.eshop.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final OrderMapper mapper;

    @PostMapping
    public void createOrder(@RequestParam String username) {
        service.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> getAllOrders(@RequestParam String username) {
        return factory.toListOrderDto(service.findAll(username));
    }
}
