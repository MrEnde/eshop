package com.shop.eshop.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JwtRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
