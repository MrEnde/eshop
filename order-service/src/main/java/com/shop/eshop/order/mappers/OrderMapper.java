package com.shop.eshop.order.mappers;

import com.shop.eshop.common.dto.OrderDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

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
