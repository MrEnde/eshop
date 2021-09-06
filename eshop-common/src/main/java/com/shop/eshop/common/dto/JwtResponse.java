package com.shop.eshop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class JwtResponse {

    @NotNull
    private String token;
}
