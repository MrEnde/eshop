package com.shop.eshop.mappers;

import com.shop.eshop.dto.OrderItemDto;
import com.shop.eshop.models.OrderItem;
import com.shop.eshop.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public abstract class OrderItemMapper {
    @Mapping(source = "id", target = "productId")
    @Mapping(source = "price", target = "pricePerProduct")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "name", target = "productName")
    @Mapping(expression = "java(1)", target = "quantity")
    public abstract OrderItemDto map(Product product);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "pricePerProduct", target = "pricePerProduct")
    @Mapping(source = "price", target = "price")
    @Mapping(expression = "java(orderItem.getProduct().getName())", target = "productName")
    @Mapping(source = "quantity", target = "quantity")
    public abstract OrderItemDto map(OrderItem orderItem);
}
