package com.chatop.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewMessage(
    @NotBlank
    @Size(max = 2000, message = "{validation.name.size.too_long}")
    String message,
    @NotNull
    @JsonProperty("user_id")
    int userId,
    @NotNull
    @JsonProperty("rental_id")
    int rentalId
) {}