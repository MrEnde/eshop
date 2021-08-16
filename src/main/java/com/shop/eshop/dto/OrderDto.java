package com.shop.eshop.dto;

import com.shop.eshop.models.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private BigDecimal price;
    private String phone;
    private String address;
    private List<OrderItemDto> items;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.price = order.getPrice();
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.items = order.getItems()
                .parallelStream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList());
    }
}
