package com.shop.eshop.category;

import com.shop.eshop.common.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface CategoryMapper {
    @Mapping( expression = "java(category.getProducts().stream().map(product -> product.getId()).toList())", target = "products")
    CategoryDto map(Category category);
}
