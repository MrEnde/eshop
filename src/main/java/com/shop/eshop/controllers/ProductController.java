package com.shop.eshop.controllers;

import com.shop.eshop.models.Product;
import com.shop.eshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/products/")
    public List<Product> findAllProducts() {
        return service.findAllProducts();
    }

    @GetMapping("/products/find_by_price/min/{price}")
    public List<Product> findByMinPrice(@PathVariable Long price) {
        return service.findByMinPrice(price);
    }

    @GetMapping("/products/find_by_price/max/{price}")
    public List<Product> findByMaxPrice(@PathVariable Long price) {
        return service.findByMaxPrice(price);
    }

    @PostMapping("/products/add")
    public Long createNewProduct(@RequestParam String name, @RequestParam Long price) {
        return service.createNewProduct(name, price);
    }

    @GetMapping ("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/products/find_by_price")
    public List<Product> findAllByPriceBetween(@RequestParam Long min, @RequestParam Long max) {
        return service.findByPriceBetween(min, max);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
