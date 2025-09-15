package com.konecta.order_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemDto {

    @NotNull
    private Long productId;

    @NotNull
    @Positive
    private double price;

    @Positive(message = "must be a positive integer")
    private int quantity;
}
