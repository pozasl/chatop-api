package com.chatop.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * New user DTO.
 */
public record NewUser(
    @NotBlank(message = "Name is mandatory")
    @Size(max = 255, message = "{validation.name.size.too_long}")
    String name,

    @Email(message = "Invalid email")
    @Size(max = 255, message = "{validation.name.size.too_long}")
    String email,

    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Size(max = 255, message = "{validation.name.size.too_long}")
    String password
) {}