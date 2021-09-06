package com.shop.eshop.order;

import com.shop.eshop.common.dto.OrderItemDto;
import com.shop.eshop.common.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface OrderItemMapper {
    @Mapping(source = "id", target = "productId")
    @Mapping(source = "price", target = "pricePerProduct")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "name", target = "productName")
    @Mapping(expression = "java(1)", target = "quantity")
    OrderItemDto map(ProductDto product);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "pricePerProduct", target = "pricePerProduct")
    @Mapping(source = "price", target = "price")
    @Mapping(expression = "java(orderItem.getProduct().getName())", target = "productName")
    OrderItemDto map(OrderItem orderItem);
}
