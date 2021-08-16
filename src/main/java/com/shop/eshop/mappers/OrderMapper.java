package com.shop.eshop.mappers;

import com.shop.eshop.dto.OrderDto;
import com.shop.eshop.models.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public OrderDto toOrderDto(Order order) {
        return new OrderDto(order);
    }

    public List<OrderDto> toListOrderDto(List<Order> orders) {
        return orders
                .parallelStream()
                .map(OrderDto::new)
                .toList();
    }
}
