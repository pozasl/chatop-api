package com.chatop.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;
import com.chatop.api.model.ResponseMessageInfo;
import com.chatop.api.service.FileStorageService;
import com.chatop.api.service.FileStorageServiceImpl;
import com.chatop.api.service.RentalService;
import com.chatop.api.service.RentalServiceImpl;

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

    @GetMapping("/rentals")
    public List<Rental> getRentals() throws Exception {
        return rentalService.getAllRentals();
    }

    @PostMapping("/rentals")
    public ResponseMessageInfo create(@Valid @RequestParam NewRental newRental) throws Exception {
        String imgSrc = fileStorageService.saveFileAs(newRental.getFile());
        newRental.setPicture(imgSrc);
        rentalService.createRental(newRental);
        return new ResponseMessageInfo("Rental created !");
    }

    @GetMapping("/rentals/{id}")
    public Rental getRentalById(@PathVariable int id) throws Exception {
        return rentalService.getRentalById(id);
    }

    @PutMapping("/rentals/{id}")
    public ResponseMessageInfo updateRentalById(@PathVariable int id, @Valid @RequestBody Rental rental) throws Exception {
        rentalService.saveRentalById(id, rental);
        return new ResponseMessageInfo("Rental created !");
    }
}
