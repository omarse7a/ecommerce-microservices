package com.konecta.order_service.controller;

import com.konecta.order_service.dto.OrderRequest;
import com.konecta.order_service.dto.OrderResponse;
import com.konecta.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public void getAllOrders(@RequestParam Long userId) {

    }

    @GetMapping("{orderId}")
    public void getOrder(@PathVariable Long orderId, @RequestParam Long userId) {

    }

    @PatchMapping("{orderId}")
    public void updateOrderStatus(@PathVariable Long orderId, @RequestParam Long userId, @RequestParam String status) {

    }
}
