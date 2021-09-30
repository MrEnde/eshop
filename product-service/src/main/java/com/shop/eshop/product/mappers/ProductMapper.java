package com.shop.eshop.product.mappers;

import com.shop.eshop.common.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;


import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper {
    @Mapping(expression = "java(product.getCategory().getTitle())", target = "category")
    ProductDto map(Product product);

    List<ProductDto> map(List<Product> products);

    default Page<ProductDto> map(Page<Product> products) {
        return products.map(this::map);
    }
}
