package com.shop.eshop.product.services;

import com.shop.eshop.common.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductSpecifications specifications;

    public Page<Product> findPage(
            int pageIndex, int pageSize,
            BigDecimal maxPrice, BigDecimal minPrice, String title
    ) {
        Specification<Product> spec = Specification.where(null);
        if (maxPrice != null) {
            spec = spec.and(specifications.priceLessOrEqualsThan(maxPrice));
        }
        if (minPrice != null) {
            spec = spec.and(specifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (title != null) {
            spec = spec.and(specifications.titleLike(title));
        }
        return repository.findAll(spec, PageRequest.of(pageIndex, pageSize));
    }

    public Optional<List<Product>> findAll() {
        return Optional.of(repository.findAll());
    }

    public Optional<Product> findById(@NotNull Long id) {
        return repository.findById(id);
    }

    public Long createNewProduct(@NotNull String name, @NotNull BigDecimal price) {
        if (price.intValue() <= 0) {
            throw new ResourceNotFoundException("Can't create a product with a price " + price);
        }
        var product = Product.builder()
                .name(name)
                .price(price)
                .build();
        return repository.save(product).getId();
    }

    public List<Product> findByPriceBetween(@NotNull BigDecimal min, @NotNull BigDecimal max) {
        return repository.findAllByPriceBetween(min, max);
    }

    public void deleteById(Long id) {
        if (id < 0) {
            throw new ResourceNotFoundException("Product not found with " + id);
        }
        repository.deleteById(id);
    }
}
