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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Rentals controller.
 */
@RestController
@RequestMapping(value="/api/rentals", produces=MediaType.APPLICATION_JSON_VALUE)
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
  @GetMapping
  public RentalsCollection getRentals() {
    List<Rental> rentals = rentalService.getAllRentals();
    return new RentalsCollection(rentals);
  }

  /**
   * Create a new Rental.
   *
   * @param name Rental's name
   * @param surface Rental's surface
   * @param price Rental's price
   * @param picture Rental's picture
   * @param description Rental's description
   * @param auth User's authentication
   * @return A confirmation response message
   * @throws FileStorageException throwed when the file couldn't be stored
   */
  @Operation(summary = "Add a new Rental")
  @SecurityRequirement(name = "Authorization")
  @PostMapping(
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
  )
  public ResponseMessage create(
      @RequestParam(required = true)
      @NotBlank(message = "Name is mandatory")
      @Size(max = 255, message = "{validation.name.size.too_long}")
      String name,

      @RequestParam
      @NotNull
      @PositiveOrZero(message = "Surface must be a positive number")
      int surface,

      @RequestParam
      @NotNull
      @PositiveOrZero(message = "Price must be a positive number")
      int price,

      @RequestPart(required = true)
      @NotNull
      MultipartFile picture,

      @RequestParam
      @NotNull(message = "Missing description parameter")
      @Size(max = 2000, message = "{validation.name.size.too_long}")
      String description,

      Authentication auth
  )
      throws FileStorageException {
    String imgSrc = fileStorageService.saveFile(picture);
    try {
      NewRental newRental = new NewRental(name, surface, price, picture, description);
      rentalService.createRental(newRental, auth.getName(), imgSrc);
    } catch (Exception e) {
      fileStorageService.deleteFile(imgSrc);
      throw e;
    }
    return responseMessageFactory.makeResponseMessage("Rental created !");
  }

  @Operation(summary = "Get a Rental by its id")
  @SecurityRequirement(name = "Authorization")
  @GetMapping("/{id}")
  public Rental getRentalById(@PathVariable int id) throws ResourceNotFoundException {
    return rentalService.getRentalById(id);
  }

  /**
   * Update a rental by its id.
   *
   * @param id the rental's id to update
   * @param name Rental's name
   * @param surface Rental's surface
   * @param price Rental's price
   * @param description Rental's description
   * @param auth the user's authentication
   * @return A confirmation response message
   * @throws ResourceNotFoundException throwed for unknown rental id
   */
  @Operation(summary = "Update the Rental with id")
  @SecurityRequirement(name = "Authorization")
  @PutMapping(
      value = "/{id}",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
  public ResponseMessage updateRentalById(
      @PathVariable int id,
      @RequestParam(required = true)
      @NotBlank(message = "Name is mandatory")
      @Size(max = 255, message = "{validation.name.size.too_long}")
      String name,

      @RequestParam
      @NotNull
      @PositiveOrZero(message = "Surface must be a positive number")
      int surface,

      @RequestParam
      @NotNull
      @PositiveOrZero(message = "Price must be a positive number")
      int price,

      @RequestParam
      @NotNull(message = "Missing description parameter")
      @Size(max = 2000, message = "{validation.name.size.too_long}")
      String description,
      Authentication auth
  ) throws ResourceNotFoundException {
    NewRental newRental = new NewRental(name, surface, price, null, description);
    rentalService.saveRentalById(id, newRental, auth.getName());
    return responseMessageFactory.makeResponseMessage("Rental updated !");
  }
}
