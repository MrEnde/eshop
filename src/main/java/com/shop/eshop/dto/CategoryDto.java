package com.shop.eshop.dto;

import com.shop.eshop.models.Category;
import com.shop.eshop.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;
    private List<Long> products;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.products = category.getProducts()
                .parallelStream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }
}
