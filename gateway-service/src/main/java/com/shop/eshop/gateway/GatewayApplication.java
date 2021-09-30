package com.shop.eshop.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class GatewayApplication {
    public static void main(String[] args) {
            SpringApplication.run(GatewayApplication.class, args);
        }

    @Bean
    public RouteLocator applicationRouterLocation(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service",
                        route -> route.path("lb://products-service").uri("/api/v1/products/**")
                )
                .route("auth-service",
                        router -> router.path("lb://auth-service").uri("/api/v1/auth/**")
                )
                .route("cart-service",
                        router -> router.path("lb://cart-service").uri("/api/v1/cart/**")
                )
                .route("category-service",
                        router -> router.path("lb://category-service").uri("/api/v1/category/**")
                )
                .route("order-service",
                        router -> router.path("lb://order-service").uri("/api/v1/order/**")
                )
                .build();
    }
}
