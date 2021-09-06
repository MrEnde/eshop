package com.shop.eshop.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDto {
    @NotNull
    private List<OrderItemDto> items;

    @NotNull
    private BigDecimal price;
}
