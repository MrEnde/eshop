package com.shop.eshop.cart.mappers;

import com.shop.eshop.common.dto.CartDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CartMapper {
    CartDto map(Cart cart);
}
