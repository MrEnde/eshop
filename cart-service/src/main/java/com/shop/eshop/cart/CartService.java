package com.shop.eshop.cart;

import com.shop.eshop.common.dto.OrderItemDto;
import com.shop.eshop.common.dto.ProductDto;
import com.shop.eshop.common.exceptions.ResourceNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CartService {
    @Getter
    private Cart cart;
    private final RestTemplate restTemplate;

    public void add(Long productId) {
        if(!cart.add(productId)) {
            // TODO Create endpoint for creating OrderItem
            cart.add(restTemplate.getForObject("http://product-service/api/v1/order/create_item/{id}", OrderItemDto.class, productId));
//            productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"))
        }
    }

    public void remove(Long productId) {
        if(!cart.delete(productId)) {
            // TODO Select all actions with RestTemplate into a separate class
            cart.delete(restTemplate.getForObject("http://product-service/api/v1/products/{id}", ProductDto.class, productId));
        }
    }

    public void decrement(Long productId) {
        cart.changeQuantity(productId, -1);
    }

    public void clear() {
        cart.clear();
    }
}
