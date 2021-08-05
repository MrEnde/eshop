package com.shop.eshop.entities;

import com.shop.eshop.dto.OrderItemDto;
import com.shop.eshop.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@Slf4j
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal price;

    public Cart() {
        items = new ArrayList<>();
        price = BigDecimal.ZERO;
    }

    // Cannot invoke "java.util.List.iterator()" because "this.items" is null
//    @PostConstruct
//    public void init() {
//        this.items = new ArrayList<>();
//        this.price = BigDecimal.ZERO;
//    }

    public void clear() {
        items.clear();
        price = BigDecimal.ZERO;
    }

    public boolean add(Long productId) {
        for (OrderItemDto item : items) {
            if (item.getProductId().equals(productId)) {
                item.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(Product product) {
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public boolean delete(Long productId) {
        for (OrderItemDto item : items) {
            if (item.getProductId().equals(productId) && item.getQuantity() > 1) {
                item.changeQuantity(-1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void delete(Product product) {
        int index = -1;
        for (OrderItemDto item : items) {
            if (item.getProductId().equals(product.getId())) {
                index = items.indexOf(item);
            }
        }
        if (index != -1) {
            items.remove(index);
        }
        recalculate();
    }

    private void recalculate() {
        price = BigDecimal.ZERO;
        for (OrderItemDto item : items) {
            price = price.add(item.getPrice());
        }
    }
}
