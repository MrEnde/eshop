package com.shop.eshop.mappers;

import com.shop.eshop.dto.CategoryDto;
import com.shop.eshop.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public abstract class CategoryMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping( expression = "java(category.getProducts().stream().map(product -> product.getId()).toList())", target = "products")
    public abstract CategoryDto map(Category category);

}
