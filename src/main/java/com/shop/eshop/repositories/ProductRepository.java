package com.shop.eshop.repositories;

import com.shop.eshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findAllByPriceLessThanEqual(BigDecimal price);
    List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max);
}
