package com.shop.eshop.controllers;

import com.shop.eshop.models.Product;
import com.shop.eshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final int DEFAULT_SIZE_PAGE = 10;

    @GetMapping("")
    public List<Product> findAllProducts() {
        return service.findAllProducts();
    }

    @GetMapping("page")
    public Page<Product> findAllProductsByPage(@RequestParam(name = "page") int pageIndex) {
        return service.findPage(pageIndex - 1, DEFAULT_SIZE_PAGE);
    }

    @GetMapping("find_by_price/min/{price}")
    public List<Product> findByMinPrice(@PathVariable Long price) {
        return service.findByMinPrice(price);
    }

    @GetMapping("find_by_price/max/{price}")
    public List<Product> findByMaxPrice(@PathVariable Long price) {
        return service.findByMaxPrice(price);
    }

    @PostMapping("add/")
    public Long createNewProduct(@RequestParam String name, @RequestParam Long price) {
        return service.createNewProduct(name, price);
    }

    @GetMapping ("{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("find_by_price")
    public List<Product> findAllByPriceBetween(@RequestParam Long min, @RequestParam Long max) {
        return service.findByPriceBetween(min, max);
    }

    @GetMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
