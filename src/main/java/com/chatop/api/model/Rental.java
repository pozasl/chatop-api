package com.chatop.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rental Model DTO.
 */
public record Rental(
    int id,

    String name,

    int surface,

    int price,

    String picture,

    String description,

    @JsonProperty("owner_id")
    int ownerId,

    @JsonProperty("created_at")
    String createdAt,

    @JsonProperty("updated_at")
    String updatedAt
) {}
