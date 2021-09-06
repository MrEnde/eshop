package com.shop.eshop.endpoints;

import com.shop.eshop.mappers.ProductMapper;
import com.shop.eshop.services.ProductService;
import com.shop.eshop.soap.products.GetAllProductsRequest;
import com.shop.eshop.soap.products.GetAllProductsResponse;
import com.shop.eshop.soap.products.GetProductByIdRequest;
import com.shop.eshop.soap.products.GetProductByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8189/api/v1/ws/products";
    private final ProductService service;
    private final ProductMapper mapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getGroupByTitle(@RequestPayload GetProductByIdRequest request) {
        var response = new GetProductByIdResponse();
        response.setProduct(
                mapper.mapSoap(
                        service.findById(request.getId()).get()
                )
        );
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProductsResponse(@RequestPayload GetAllProductsRequest request) {
        var response = new GetAllProductsResponse();
        var products = service.findAll().get()
                .stream()
                .map(mapper::mapSoap)
                .toList();
        response.getProducts().addAll(products);
        return response;
    }
}
