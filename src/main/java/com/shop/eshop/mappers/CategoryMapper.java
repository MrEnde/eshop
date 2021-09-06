package com.shop.eshop.mappers;

import com.shop.eshop.dto.CategoryDto;
import com.shop.eshop.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface CategoryMapper {
    @Mapping( expression = "java(category.getProducts().stream().map(product -> product.getId()).toList())", target = "products")
    CategoryDto map(Category category);
}
