package com.shop.eshop.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
public class OrderDto {

    @NotNull
    private Long id;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private List<OrderItemDto> items;
}
