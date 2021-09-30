package com.shop.eshop.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private List<Long> products;
}
