package com.konecta.order_service.dto;

import com.konecta.order_service.enums.OrderStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    @NotNull
    private Long userId;

    private OrderStatus status = OrderStatus.PENDING;

    @NotNull
    @PositiveOrZero
    private double totalAmount;

    @Valid
    @NotEmpty
    private List<OrderItemDto> items;

}
