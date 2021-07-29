package com.shop.eshop.services;

import com.shop.eshop.models.Product;
import com.shop.eshop.repositories.ProductRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public Page<Product> findPage(int pageIndex, int pageSize) {
        return repository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findById(@NotNull Long id) {
        return repository.findById(id);
    }

    public Long createNewProduct(@NotNull String name, @NotNull Long price) {
        if (price <= 0) {
            return (long) -1;
        }
        var product = new Product();
        product.setName(name);
        product.setPrice(price);
        return repository.save(product).getId();
    }

    public List<Product> findByMaxPrice(@NotNull Long price) {
        if(price < 0) {
            return List.of();
        }
        return repository.findAllByPriceLessThanEqual(price);
    }

    public List<Product> findByMinPrice(@NotNull Long price) {
        if(price < 0) {
            return List.of();
        }
        return repository.findAllByPriceGreaterThanEqual(price);
    }

    public List<Product> findByPriceBetween(@NotNull Long min, @NotNull Long max) {
        if (min < 0 || max < 0) {
            return List.of();
        }
        return repository.findAllByPriceBetween(min, max);
    }

    public void deleteById(Long id) {
        if (id < 0) {
            return;
        }
        repository.deleteById(id);
    }
}
