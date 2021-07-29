package com.shop.eshop.controllers;

import com.shop.eshop.entities.Cart;
import com.shop.eshop.exception.ResourceNotFoundException;
import com.shop.eshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;
    private final ProductService service;

    @GetMapping
    public Cart getCart() {
        return cart;
    }

    @GetMapping("/add/{productId}")
    public void add(@PathVariable Long productId) {
        if (!cart.add(productId)) {
            cart.add(service.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found id: " + productId)));
        }
    }

    @DeleteMapping("/delete/{productId}")
    public void delete(@PathVariable Long productId) {
        if (!cart.delete(productId)) {
            cart.delete(service.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Unable delete product to cart. Product not found id: " + productId)));
        }
    }

    @GetMapping("/clear")
    public void clear() {
        cart.clear();
    }
}
