package com.shop.eshop.cart.controllers;

import com.shop.eshop.common.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;
    private final CartMapper mapper;

    @GetMapping
    public CartDto getCart() {
        return mapper.map(service.getCart());
    }

    @GetMapping("/add/{productId}")
    public void add(@PathVariable Long productId) {
        service.add(productId);
    }

    @GetMapping("/delete/{productId}")
    public void remove(@PathVariable Long productId) {
        service.remove(productId);
    }

    @GetMapping("/decrement/{productId}")
    public void decrement(@PathVariable Long productId) {
        service.decrement(productId);
    }

    @GetMapping("/clear")
    public void clear() {
        service.clear();
    }
}
