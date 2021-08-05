package com.shop.eshop.controllers;

import com.shop.eshop.entities.Cart;
import com.shop.eshop.exception.ResourceNotFoundException;
import com.shop.eshop.services.CartService;
import com.shop.eshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @GetMapping
    public Cart getCart(@RequestParam String username) {
        return service.getCartByUsername(username);
    }

    @GetMapping("/add/{productId}")
    public void add(@PathVariable Long productId, @RequestParam String username) {
        service.add(username, productId);
    }

    @GetMapping("/delete/{productId}")
    public void delete(@PathVariable Long productId, @RequestParam String username) {
        service.delete(username, productId);
    }

    @GetMapping("/clear")
    public void clear(@RequestParam String username) {
        service.clear(username);
    }
}
