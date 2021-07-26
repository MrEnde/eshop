package com.shop.eshop.services;

import com.shop.eshop.models.Category;
import com.shop.eshop.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }
}
