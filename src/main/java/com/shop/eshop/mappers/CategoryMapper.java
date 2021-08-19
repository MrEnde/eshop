package com.shop.eshop.mappers;

import com.shop.eshop.dto.CategoryDto;
import com.shop.eshop.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(category);
    }
}
