package com.shop.eshop.mappers;

import com.shop.eshop.dto.ProductDto;
import com.shop.eshop.models.Product;
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

    com.shop.eshop.soap.products.Product mapSoap(Product product);

    List<ProductDto> map(List<Product> products);

    default Page<ProductDto> map(Page<Product> products) {
        return products.map(this::map);
    }
}
