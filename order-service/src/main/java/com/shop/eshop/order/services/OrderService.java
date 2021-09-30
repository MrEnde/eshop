package com.shop.eshop.order.services;

import com.shop.eshop.common.dto.CartDto;
import com.shop.eshop.common.dto.ProductDto;
import com.shop.eshop.common.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    @Transactional
    public void createOrder(String address, String phone) {
        var cart = restTemplate.getForObject("http://cart-service/api/v1/cart", CartDto.class);

        var order = Order.builder()
                .price(cart.getPrice())
                .items(new ArrayList<>())
                .address(address)
                .phone(phone)
                .build();
        for (var item : cart.getItems()) {
            var product = restTemplate.getForObject(
                    "http://product-service/api/v1/product/{id}",
                    ProductDto.class,
                    item.getProductId()
            );
            var orderItem = OrderItem.builder()
                    .order(order)
                    .quantity(item.getQuantity())
                    .price(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .pricePerProduct(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .product(product.getId())
                    .build();
            order.getItems().add(orderItem);
        }
        repository.save(order);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }
}
