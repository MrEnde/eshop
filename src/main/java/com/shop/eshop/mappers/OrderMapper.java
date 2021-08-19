package com.shop.eshop.mappers;

import com.shop.eshop.dto.OrderDto;
import com.shop.eshop.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public abstract class OrderMapper {
    @Autowired
    protected OrderItemMapper orderItemMapper;

    @Mapping(source = "id", target = "id")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "address", target = "address")
    @Mapping(expression = "java(order.getItems().parallelStream().map(orderItemMapper::map).toList())", target = "items")
    public abstract OrderDto map(Order order);

    public abstract List<OrderDto> map(List<Order> orders);
}
