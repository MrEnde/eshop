package com.shop.eshop.controllers;

import com.shop.eshop.dto.ProductDto;
import com.shop.eshop.exception.ResourceNotFoundException;
import com.shop.eshop.mappers.ProductMapper;
import com.shop.eshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProductMapper factory;
    private final int DEFAULT_SIZE_PAGE = 10;

    @GetMapping("")
    public Page<ProductDto> findAllProductsByPage(@RequestParam(name = "page") int pageIndex) {
        return factory.toProductDtoPage(service.findPage(pageIndex - 1, DEFAULT_SIZE_PAGE));
    }

    @PostMapping("/add/")
    public Long createNewProduct(@RequestParam String name, @RequestParam BigDecimal price) {
        return service.createNewProduct(name, price);
    }

    @GetMapping ("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        var product = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return factory.toProductDto(product);
    }

    @GetMapping("/find_by_price")
    public List<ProductDto> findAllByPriceBetween(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return factory.toProductDtoList(service.findByPriceBetween(min, max));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
