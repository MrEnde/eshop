package com.shop.eshop.services;

import com.shop.eshop.entities.Cart;
import com.shop.eshop.exception.MarketError;
import com.shop.eshop.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private Map<String, Cart> carts;
    private final ProductService productService;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCartByUsername(String username) {
        carts.computeIfAbsent(username, value -> new Cart());

        return carts.get(username);
    }

    public void add(String username, Long productId) {
        carts.computeIfAbsent(username, value -> new Cart());
        var cart = carts.get(username);
        if(!cart.add(productId)) {
            cart.add(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
        }
    }

    public void delete(String username, Long productId) {
        if (!carts.containsKey(username)) {
            throw new ResourceNotFoundException("Product not found");
        }
        var cart = carts.get(username);
        if(!cart.delete(productId)) {
            cart.delete(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
        }
    }

    public void clear(String username) {
        if (!carts.containsKey(username)) {
            return;
        }
        carts.get(username).clear();
    }
}
