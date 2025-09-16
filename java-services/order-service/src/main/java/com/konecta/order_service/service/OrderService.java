package com.konecta.order_service.service;

import com.konecta.order_service.client.CartClient;
import com.konecta.order_service.dto.OrderRequest;
import com.konecta.order_service.dto.OrderResponse;
import com.konecta.order_service.entity.Order;
import com.konecta.order_service.entity.OrderItem;
import com.konecta.order_service.enums.OrderStatus;
import com.konecta.order_service.exception.OrderNotFoundException;
import com.konecta.order_service.mapper.OrderItemMapper;
import com.konecta.order_service.mapper.OrderMapper;
import com.konecta.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderItemMapper itemMapper;
    private final OrderRepository orderRepository;
    private final CartClient cart;

    public OrderService(OrderMapper orderMapper, OrderItemMapper itemMapper, OrderRepository orderRepository, CartClient cart) {
        this.orderMapper = orderMapper;
        this.itemMapper = itemMapper;
        this.orderRepository = orderRepository;
        this.cart = cart;
    }

    public OrderResponse createOrder(OrderRequest request) {
        Order order = orderMapper.toEntity(request);
        List<OrderItem> items = request.getItems()
                .stream()
                .map(itemMapper::toEntity)
                .toList();
        order.setItems(items);
        items.forEach(item -> item.setOrder(order));
        orderRepository.save(order);

        cart.RefreshCartTtl(request.getUserId(), 7);

        return orderMapper.toDto(order);
    }

    public List<OrderResponse> fetchAllOrdersByUser(Long userId) {
        return orderRepository.findAllByUserId(userId)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public OrderResponse fetchUserOrderById(Long orderId, Long userId) {
        return orderMapper.toDto(orderRepository
                .findByIdAndUserId(orderId, userId));
    }

    public String updateOrderStatus(Long orderId, Long userId, String status) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId);
        if (order == null) {
            throw new OrderNotFoundException("Order not found for user " + userId);
        }
        order.setStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);

        return "Order " + orderId + " status updated to " + status;
    }
}
