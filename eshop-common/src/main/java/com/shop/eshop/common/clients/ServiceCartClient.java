package com.shop.eshop.common.clients;

import com.shop.eshop.common.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-cart")
public interface ServiceCartClient {
    @GetMapping
    CartDto getCart();

    @GetMapping("/add/{productId}")
    void add(@PathVariable Long productId);

    @GetMapping("/delete/{productId}")
    void remove(@PathVariable Long productId);

    @GetMapping("/decrement/{productId}")
    void decrement(@PathVariable Long productId);

    @GetMapping("/clear")
    void clear();
}
