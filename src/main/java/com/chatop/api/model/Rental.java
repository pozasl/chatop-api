package com.chatop.api.model;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record Rental (

    int id,

    @NotBlank
    String name,

    @PositiveOrZero
    int surface,

    @PositiveOrZero
    int price,

    @URL
    String picture,

    @NotNull
    String description,

    @Positive
    @JsonProperty("owner_id")
    int ownerId,

    @JsonProperty("created_at")
    String createdAt,

    @JsonProperty("updated_at")
    String updatedAt
) {}
