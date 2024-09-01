package com.chatop.api.controller;

import com.chatop.api.exception.FileStorageException;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalsCollection;
import com.chatop.api.model.ResponseMessage;
import com.chatop.api.model.ResponseMessageFactory;
import com.chatop.api.model.ResponseMessageFactoryImpl;
import com.chatop.api.service.FileStorageService;
import com.chatop.api.service.FileStorageServiceImpl;
import com.chatop.api.service.RentalService;
import com.chatop.api.service.RentalServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

/**
 * Rentals controller.
 */
@RestController
@RequestMapping("/api")
public class RentalController {

  private RentalService rentalService;
  private FileStorageService fileStorageService;
  private ResponseMessageFactory responseMessageFactory;

  /**
   * Rentals controller.
   *
   * @param rentalService Rental manager service
   * @param fileStorageService File storage service
   */
  @Autowired
  public RentalController(
      RentalServiceImpl rentalService,
      FileStorageServiceImpl fileStorageService
  ) {
    this.rentalService = rentalService;
    this.fileStorageService = fileStorageService;
    this.responseMessageFactory = ResponseMessageFactoryImpl.create();
  }

  @Operation(summary = "Get all Rentals")
  @GetMapping("/rentals")
  public RentalsCollection getRentals() {
    List<Rental> rentals = rentalService.getAllRentals();
    return new RentalsCollection(rentals);
  }

  /**
   * Create a new Rental.
   *
   * @param newRental New Rental ro creatz
   * @param auth User's authentication
   * @return A confirmation response message
   * @throws FileStorageException throwed when the file couldn't be stored
   */
  @Operation(summary = "Add a new Rental")
  @PostMapping("/rentals")
  public ResponseMessage create(@Valid @ModelAttribute NewRental newRental, Authentication auth)
      throws FileStorageException {
    String imgSrc = fileStorageService.saveFile(newRental.picture());
    try {
      rentalService.createRental(newRental, auth.getName(), imgSrc);
    } catch (Exception e) {
      fileStorageService.deleteFile(imgSrc);
      throw e;
    }
    return responseMessageFactory.makeResponseMessage("Rental created !");
  }

  @Operation(summary = "Get a Rental by its id")
  @GetMapping("/rentals/{id}")
  public Rental getRentalById(@PathVariable int id) throws ResourceNotFoundException {
    return rentalService.getRentalById(id);
  }

  /**
   * Update a rental by its id.
   *
   * @param id the rental's id to update
   * @param newRental the new Rental update
   * @param auth the user's authentication
   * @return A confirmation response message
   * @throws ResourceNotFoundException throwed for unknown rental id
   */
  @Operation(summary = "Update the Rental with id")
  @PutMapping("/rentals/{id}")
  public ResponseMessage updateRentalById(
      @PathVariable int id,
      @Valid @ModelAttribute NewRental newRental,
      Authentication auth
  ) throws ResourceNotFoundException {
    rentalService.saveRentalById(id, newRental, auth.getName());
    return responseMessageFactory.makeResponseMessage("Rental updated !");
  }
}
