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
    public ResponseMessageInfo create(@Valid @ModelAttribute NewRental newRental, Authentication auth) throws Exception {
        String imgSrc = fileStorageService.saveFile(newRental.getPicture());
        try {
            rentalService.createRental(newRental, auth.getName(), imgSrc);
        } catch(Exception e)
        {
            fileStorageService.deleteFile(imgSrc);
            throw e;
        }
        
        return new ResponseMessageInfo("Rental created !");
    }

    @GetMapping("/rentals/{id}")
    public Rental getRentalById(@PathVariable int id) throws Exception {
        return rentalService.getRentalById(id);
    }

    @PutMapping("/rentals/{id}")
    public ResponseMessageInfo updateRentalById(@PathVariable int id, @Valid @ModelAttribute NewRental newRental, Authentication auth) throws Exception {
        rentalService.saveRentalById(id, newRental, auth.getName());
        return new ResponseMessageInfo("Rental updated !");
    }
}
