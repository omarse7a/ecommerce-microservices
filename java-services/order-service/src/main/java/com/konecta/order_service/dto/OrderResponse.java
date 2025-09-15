package com.konecta.order_service.dto;

import com.konecta.order_service.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private Long id;

    private Long userId;

    private OrderStatus status = OrderStatus.PENDING;

    private double totalAmount;

    private List<OrderItemDto> items;

}