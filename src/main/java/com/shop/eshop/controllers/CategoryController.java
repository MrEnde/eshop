package com.shop.eshop.controllers;

import com.shop.eshop.dto.CategoryDto;
import com.shop.eshop.exception.ResourceNotFoundException;
import com.shop.eshop.factory.CategoryFactory;
import com.shop.eshop.services.CategoryService;
import lombok.RequiredArgsConstructor;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    private final CategoryFactory factory;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        var category = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found, id: " + id));
        return factory.toCategoryDto(category);
    }
}
