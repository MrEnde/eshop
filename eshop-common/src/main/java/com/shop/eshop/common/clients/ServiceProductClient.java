package com.shop.eshop.common.clients;

import com.shop.eshop.common.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "service-product")
public interface ServiceProductClient {
    @PostMapping("/add/")
    Long createNewProduct(@RequestParam String name, @RequestParam BigDecimal price);

    @GetMapping("/{id}")
    ProductDto findById(@PathVariable Long id);

    @GetMapping
    Page<ProductDto> findAll(
            @RequestParam(name = "page", defaultValue = "1") int pageIndex,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "title", required = false) String title
    );

    @GetMapping("/find_by_price")
    List<ProductDto> findAllByPriceBetween(@RequestParam BigDecimal min, @RequestParam BigDecimal max);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id);
}
