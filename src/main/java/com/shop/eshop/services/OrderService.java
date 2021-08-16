package com.shop.eshop.services;

import com.shop.eshop.exception.ResourceNotFoundException;
import com.shop.eshop.models.Order;
import com.shop.eshop.models.OrderItem;
import com.shop.eshop.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;

    @Transactional
    public void createOrder(String username) {
        var cart = cartService.getCartByUsername(username);

        var order = Order.builder()
                .price(cart.getPrice())
                .items(new ArrayList<>())
                .user(userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found")))
                .build();
        for (var item : cart.getItems()) {
            var product = productService.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            var orderItem = OrderItem.builder()
                    .order(order)
                    .quantity(item.getQuantity())
                    .price(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .pricePerProduct(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .product(product)
                    .build();
            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        cart.clear();
    }

    public List<Order> findAll(String username) {
        return orderRepository.findAllByUser(userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }
}
