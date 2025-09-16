package com.konecta.order_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    @NotNull
    private Long productId;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @Positive(message = "must be a positive integer")
    private Integer quantity;
}
