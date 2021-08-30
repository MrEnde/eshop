package com.shop.eshop.repositories;

import com.shop.eshop.models.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @EntityGraph(value = "order-with-items")
    List<Order> findAll();
}
