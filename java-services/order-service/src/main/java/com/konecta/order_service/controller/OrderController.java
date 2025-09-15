package com.konecta.order_service.controller;

import com.konecta.order_service.dto.OrderRequest;
import com.konecta.order_service.dto.OrderResponse;
import com.konecta.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public void getAllOrders(Long userId) {

    }

    // userId query param
    public void getOrder(Long userId, Long orderId) {

    }

    // status and userId query param
    public void updateOrderStatus(Long userId, Long orderId) {

    }
}
