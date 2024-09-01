package com.chatop.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 * New Rental DTO.
 */
public record NewRental(
    @NotBlank(message = "Name is mandatory")
    @Size(max = 255, message = "{validation.name.size.too_long}")
    String name,

    @NotNull
    @PositiveOrZero(message = "Surface must be a positive number")
    int surface,

    @NotNull
    @PositiveOrZero(message = "Price must be a positive number")
    int price,

    MultipartFile picture,

    @NotNull(message = "Missing description parameter")
    @Size(max = 2000, message = "{validation.name.size.too_long}")
    String description
) {}