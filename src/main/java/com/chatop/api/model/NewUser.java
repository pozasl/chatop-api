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

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    @Size(max = 255, message = "{validation.name.size.too_long}")
    String email,

    @NotBlank(message = "Passord is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Size(max = 255, message = "{validation.name.size.too_long}")
    String password
) {}