package com.konecta.order_service.service;

import com.konecta.order_service.client.CartClient;
import com.konecta.order_service.dto.OrderRequest;
import com.konecta.order_service.dto.OrderResponse;
import com.konecta.order_service.entity.Order;
import com.konecta.order_service.entity.OrderItem;
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

        cart.RefreshCartTtl(request.getUserId(), 7);

        Order order = orderMapper.toEntity(request);
        List<OrderItem> items = request.getItems()
                .stream()
                .map(itemMapper::toEntity)
                .toList();
        order.setItems(items);
        items.forEach(item -> item.setOrder(order));
        orderRepository.save(order);

        return orderMapper.toDto(order);
    }
}
