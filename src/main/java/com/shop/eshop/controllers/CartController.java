package com.shop.eshop.controllers;

import com.shop.eshop.entities.Cart;
import com.shop.eshop.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @GetMapping
    public Cart getCart() {
        return service.getCart();
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
