package com.shop.eshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private BigDecimal price;
    private String phone;
    private String address;
    private List<OrderItemDto> items;
}
