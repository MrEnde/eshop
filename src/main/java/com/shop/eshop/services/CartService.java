package com.shop.eshop.services;

import com.shop.eshop.entities.Cart;
import com.shop.eshop.exception.ResourceNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    @Getter
    private Cart cart;
    private final ProductService productService;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public void add(Long productId) {
        if(!cart.add(productId)) {
            cart.add(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
        }
    }

    public void remove(Long productId) {
        if(!cart.delete(productId)) {
            cart.delete(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
        }
    }

    public void decrement(Long productId) {
        cart.changeQuantity(productId, -1);
    }

    public void clear() {
        cart.clear();
    }
}
