package com.shop.eshop.factory;

import com.shop.eshop.dto.OrderDto;
import com.shop.eshop.models.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderFactory {
    public OrderDto toOrderDto(Order order) {
        return new OrderDto(order);
    }

    public List<OrderDto> toListOrderDto(List<Order> orders) {
        return orders
                .parallelStream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }
}
