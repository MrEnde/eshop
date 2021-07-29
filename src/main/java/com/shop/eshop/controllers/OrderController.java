package com.shop.eshop.controllers;

import com.shop.eshop.dto.OrderDto;
import com.shop.eshop.factory.OrderFactory;
import com.shop.eshop.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final OrderFactory factory;

    @PostMapping
    public void createOrder() {
        service.createOrder();
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return factory.toListOrderDto(service.findAll());
    }
}
