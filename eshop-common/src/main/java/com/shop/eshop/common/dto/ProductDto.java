package com.shop.eshop.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String category;

    @NotNull
    private BigDecimal price;
}
