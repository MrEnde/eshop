package com.shop.eshop.common.clients;

import com.shop.eshop.common.dto.OrderDto;
import com.shop.eshop.common.dto.OrderItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-order")
public interface ServiceOrderClient {

    @GetMapping("/api/v1/orders/")
    List<OrderDto> getAllOrders();

    @PostMapping("/api/v1/orders/")
    void createOrder(@RequestParam String address, @RequestParam String phone);

    @GetMapping("/item/{id}")
    OrderItemDto findOrderItemByProductId(@PathVariable Long id);
}
