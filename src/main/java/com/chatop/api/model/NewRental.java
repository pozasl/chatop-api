package com.chatop.api.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * New Rental DTO.
 */
public record NewRental(
    String name,
    int surface,
    int price,
    MultipartFile picture,
    String description
) {}