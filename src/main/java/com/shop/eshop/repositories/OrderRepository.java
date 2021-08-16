package com.shop.eshop.repositories;

import com.shop.eshop.models.Order;
import com.shop.eshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByUser(User user);
}
