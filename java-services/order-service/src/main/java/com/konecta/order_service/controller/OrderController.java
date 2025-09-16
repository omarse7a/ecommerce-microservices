package com.konecta.order_service.controller;

import com.konecta.order_service.dto.OrderRequest;
import com.konecta.order_service.dto.OrderResponse;
import com.konecta.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
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

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(@RequestParam("userId") Long userId) {
        List<OrderResponse> response = orderService.fetchAllOrdersByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderResponse> getOrder(
            @PathVariable("orderId") Long orderId,
            @RequestParam("userId") Long userId) {
        OrderResponse response = orderService.fetchUserOrderById(orderId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{orderId}")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam Long userId,
            @RequestParam String status) {
        String message = orderService.updateOrderStatus(orderId, userId, status);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
