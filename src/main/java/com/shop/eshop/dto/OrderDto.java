package com.shop.eshop.dto;

import com.shop.eshop.models.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private BigDecimal price;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.price = order.getPrice();
    }
}
