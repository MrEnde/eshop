package com.shop.eshop.repositories;

import com.shop.eshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(Long price);
    List<Product> findAllByPriceLessThanEqual(Long price);
    List<Product> findAllByPriceBetween(Long min, Long max);
}
