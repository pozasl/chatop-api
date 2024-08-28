package com.chatop.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.exception.FileStorageException;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalsCollection;
import com.chatop.api.model.ResponseMessageInfo;
import com.chatop.api.service.FileStorageService;
import com.chatop.api.service.FileStorageServiceImpl;
import com.chatop.api.service.RentalService;
import com.chatop.api.service.RentalServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RentalController {

    private RentalService rentalService;
    private FileStorageService fileStorageService;

    @Autowired
    public RentalController(RentalServiceImpl rentalService, FileStorageServiceImpl fileStorageService) {
        this.rentalService = rentalService;
        this.fileStorageService = fileStorageService;
    }

    @Operation(summary = "Get all Rentals")
    @GetMapping("/rentals")
    public RentalsCollection getRentals() {
        List<Rental>rentals = rentalService.getAllRentals();
        return new RentalsCollection(rentals);
    }

    @Operation(summary = "Add a new Rental")
    @PostMapping("/rentals")
    public ResponseMessageInfo create(@Valid @ModelAttribute NewRental newRental, Authentication auth) throws FileStorageException {
        String imgSrc = fileStorageService.saveFile(newRental.picture());
        try {
            rentalService.createRental(newRental, auth.getName(), imgSrc);
        } catch(Exception e)
        {
            fileStorageService.deleteFile(imgSrc);
            throw e;
        }
        return new ResponseMessageInfo("Rental created !");
    }

    @Operation(summary = "Get a Rental by its id")
    @GetMapping("/rentals/{id}")
    public Rental getRentalById(@PathVariable int id) throws ResourceNotFoundException {
        return rentalService.getRentalById(id);
    }

    @Operation(summary = "Update the Rental with id")
    @PutMapping("/rentals/{id}")
    public ResponseMessageInfo updateRentalById(@PathVariable int id, @Valid @ModelAttribute NewRental newRental, Authentication auth) throws ResourceNotFoundException {
        rentalService.saveRentalById(id, newRental, auth.getName());
        return new ResponseMessageInfo("Rental updated !");
    }
}
