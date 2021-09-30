package com.shop.eshop.category;

import com.shop.eshop.common.dto.CategoryDto;
import com.shop.eshop.common.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        var category = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found, id: " + id));
        return mapper.map(category);
    }
}
