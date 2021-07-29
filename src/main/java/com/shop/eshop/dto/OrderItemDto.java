package com.shop.eshop.dto;

import com.shop.eshop.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderItemDto {
    private Long productId;
    private String productName;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
    private int quantity;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
        this.productName = product.getName();
    }

    public void changeQuantity(int amount) {
        quantity += amount;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }
}
