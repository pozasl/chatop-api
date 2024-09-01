package com.chatop.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User model DTO.
 */
public record User(
    int id,

    String name,

    String email,

    @JsonProperty("created_at")
    String createdAt,

    @JsonProperty("updated_at")
    String updatedAt
) {}
