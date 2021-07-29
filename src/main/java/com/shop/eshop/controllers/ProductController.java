package com.shop.eshop.controllers;

import com.shop.eshop.dto.ProductDto;
import com.shop.eshop.exception.ResourceNotFoundException;
import com.shop.eshop.factory.ProductFactory;
import com.shop.eshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProductFactory factory;
    private final int DEFAULT_SIZE_PAGE = 10;

    @GetMapping("")
    public List<ProductDto> findAllProducts() {
        return factory.toProductDtoList(service.findAllProducts());
    }

    @GetMapping("page")
    public Page<ProductDto> findAllProductsByPage(@RequestParam(name = "page") int pageIndex) {
        return factory.toProductDtoPage(service.findPage(pageIndex - 1, DEFAULT_SIZE_PAGE));
    }

    @GetMapping("find_by_price/min/{price}")
    public List<ProductDto> findByMinPrice(@PathVariable Long price) {
        return factory.toProductDtoList(service.findByMinPrice(price));
    }

    @GetMapping("find_by_price/max/{price}")
    public List<ProductDto> findByMaxPrice(@PathVariable Long price) {
        return factory.toProductDtoList(service.findByMaxPrice(price));
    }

    @PostMapping("add/")
    public Long createNewProduct(@RequestParam String name, @RequestParam Long price) {
        return service.createNewProduct(name, price);
    }

    @GetMapping ("{id}")
    public ProductDto findById(@PathVariable Long id) {
        var product = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return factory.toProductDto(product);
    }

    @GetMapping("find_by_price")
    public List<ProductDto> findAllByPriceBetween(@RequestParam Long min, @RequestParam Long max) {
        return factory.toProductDtoList(service.findByPriceBetween(min, max));
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
