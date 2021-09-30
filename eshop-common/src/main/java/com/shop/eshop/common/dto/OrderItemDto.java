package com.shop.eshop.common.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderItemDto {
    @NotNull
    private Long productId;

    @NotNull
    private String productName;

    @NotNull
    private BigDecimal pricePerProduct;

    @NotNull
    private BigDecimal price;

    @NotNull
    private int quantity;

    public void changeQuantity(@NotNull int amount) {
        quantity += amount;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }
}
