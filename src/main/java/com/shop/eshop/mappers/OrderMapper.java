package com.shop.eshop.mappers;

import com.shop.eshop.dto.OrderDto;
import com.shop.eshop.models.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {OrderItemMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface OrderMapper {
    OrderDto map(Order order);

    List<OrderDto> map(List<Order> orders);
}
