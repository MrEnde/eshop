package com.shop.eshop.entities;

import com.shop.eshop.dto.OrderItemDto;
import com.shop.eshop.mappers.OrderItemMapper;
import com.shop.eshop.mappers.OrderItemMapperImpl;
import com.shop.eshop.models.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal price;
    private OrderItemMapper mapper;

    public Cart() {
        items = new ArrayList<>();
        price = BigDecimal.ZERO;
        mapper = new OrderItemMapperImpl();
    }

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
        items.add(mapper.map(product));
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

    public void changeQuantity(Long productId, int amount) {
        var iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto o = iter.next();
            if (o.getProductId().equals(productId)) {
                o.changeQuantity(amount);
                if (o.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    private void recalculate() {
        price = BigDecimal.ZERO;
        for (OrderItemDto item : items) {
            price = price.add(item.getPrice());
        }
    }
}
