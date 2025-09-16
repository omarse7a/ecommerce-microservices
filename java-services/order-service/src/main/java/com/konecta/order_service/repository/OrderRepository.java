package com.konecta.order_service.repository;

import com.konecta.order_service.dto.OrderResponse;
import com.konecta.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    Order findByIdAndUserId(Long id, Long userId);
}
