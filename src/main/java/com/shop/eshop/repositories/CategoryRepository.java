package com.shop.eshop.repositories;

import com.shop.eshop.models.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @EntityGraph(value = "category-with-products")
    Optional<Category> findById(Long id);
}
