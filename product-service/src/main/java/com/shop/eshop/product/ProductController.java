package com.shop.eshop.product;

import com.shop.eshop.common.dto.ProductDto;
import com.shop.eshop.common.exceptions.ResourceNotFoundException;
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
    private final ProductMapper mapper;
    private final int DEFAULT_SIZE_PAGE = 10;

    @PostMapping("/add/")
    public Long createNewProduct(@RequestParam String name, @RequestParam BigDecimal price) {
        return service.createNewProduct(name, price);
    }

    @GetMapping ("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        var product = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return mapper.map(product);
    }

    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(name = "page", defaultValue = "1") int pageIndex,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "title", required = false) String title
    ) {
        return mapper.map(service.findPage(pageIndex - 1, DEFAULT_SIZE_PAGE, maxPrice, minPrice, title));
    }

    @GetMapping("/find_by_price")
    public List<ProductDto> findAllByPriceBetween(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return mapper.map(service.findByPriceBetween(min, max));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
