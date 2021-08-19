package com.shop.eshop.mappers;

import com.shop.eshop.dto.ProductDto;
import com.shop.eshop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    public List<ProductDto> toProductDtoList(List<Product> products) {
        return products
                .parallelStream()
                .map(ProductDto::new)
                .toList();
    }

    public Page<ProductDto> toProductDtoPage(Page<Product> products) {
        return products.map(ProductDto::new);
    }

    public ProductDto toProductDto(Product product) {
        return new ProductDto(product);
    }
}
