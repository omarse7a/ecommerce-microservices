package com.konecta.product_service.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreationDto {
    @NotBlank
    private String name;

    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "must be greater than 0")
    private double price;

    @Min(value = 0, message = "must be a positive integer")
    private int stock = 0;
}
