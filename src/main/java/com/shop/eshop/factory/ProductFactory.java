package com.shop.eshop.factory;

import com.shop.eshop.dto.ProductDto;
import com.shop.eshop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductFactory {
    public List<ProductDto> toProductDtoList(List<Product> products) {
        return products
                .parallelStream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public Page<ProductDto> toProductDtoPage(Page<Product> products) {
        return products.map(ProductDto::new);
    }

    public ProductDto toProductDto(Product product) {
        return new ProductDto(product);
    }
}
