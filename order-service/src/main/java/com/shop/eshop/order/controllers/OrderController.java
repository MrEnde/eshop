package com.shop.eshop.order.controllers;

import com.shop.eshop.common.dto.OrderDto;
import com.shop.eshop.common.exceptions.InvalidInputDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final OrderMapper mapper;

    @PostMapping
    public void createOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
        List<String> errors = new ArrayList<>();
        if (address.isBlank()) {
            errors.add("Field 'address' cannot be null");
        }
        if (phone.isBlank()) {
            errors.add("Field 'phone' cannot be null");
        }
        if (!errors.isEmpty()) {
            throw new InvalidInputDataException(errors);
        }
        service.createOrder(address, phone);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return mapper.map(service.findAll());
    }
}
