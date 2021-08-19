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
public abstract class ProductMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(expression = "java(product.getCategory().getTitle())", target = "category")
    @Mapping(source = "price", target = "price")
    public abstract ProductDto map(Product product);

    public abstract  List<ProductDto> map(List<Product> products);

    public Page<ProductDto> map(Page<Product> products) {
        return products.map(this::map);
    }
}
