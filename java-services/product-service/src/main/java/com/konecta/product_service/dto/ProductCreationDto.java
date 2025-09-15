package com.konecta.product_service.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreationDto {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Positive
    private double price;

    @NotNull
    @PositiveOrZero(message = "must be a non-negative integer")
    private int stock = 0;
}
