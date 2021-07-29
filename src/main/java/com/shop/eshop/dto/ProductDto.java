package com.shop.eshop.dto;

import com.shop.eshop.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String category;
    private long price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory().getTitle();
        this.price = product.getPrice();
    }
}
