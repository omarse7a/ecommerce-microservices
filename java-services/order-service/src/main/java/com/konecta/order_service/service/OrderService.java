package com.konecta.order_service.service;

import com.konecta.order_service.dto.OrderRequest;
import com.konecta.order_service.dto.OrderResponse;
import com.konecta.order_service.entity.Order;
import com.konecta.order_service.mapper.OrderMapper;
import com.konecta.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public OrderResponse createOrder(OrderRequest request) {
        Order order = orderMapper.toEntity(request);
        order.getItems().forEach(item -> item.setOrder(order));
        orderRepository.save(order);
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setItems(request.getItems());
        return dto;
    }
}
