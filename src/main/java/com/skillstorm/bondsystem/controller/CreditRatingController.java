package com.skillstorm.bondsystem.controller;

import com.skillstorm.bondsystem.model.CreditRating;
import com.skillstorm.bondsystem.service.CreditRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/credit_ratings")
public class CreditRatingController {

      private final CreditRatingService creditRatingService;

      public CreditRatingController(CreditRatingService creditRatingService) {
            this.creditRatingService = creditRatingService;
      }

      @GetMapping
      public ResponseEntity<List<CreditRating>> getAllCreditRatings() {
            List<CreditRating> creditRatingList = creditRatingService.getAllCreditRating();
            return ResponseEntity.ok(creditRatingList);
      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getCreditRatingById(@PathVariable Integer id) {
            Optional<CreditRating> existingCreditRating = creditRatingService.getCreditRatingById(id);

            if (existingCreditRating.isPresent()) {
                  return ResponseEntity.ok(existingCreditRating.get());
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Credit rating not found with id: " + id);
      }

      @PostMapping
      public ResponseEntity<?> createCreditRating(@RequestBody CreditRating creditRating) {

            if (creditRating.getCreditRatingId() == null) {
                  return ResponseEntity.badRequest().body("Credit rating id is required");
            }

            if (creditRating.getCreditRatingName() == null || creditRating.getCreditRatingName().isBlank()) {
                  return ResponseEntity.badRequest().body("Credit rating name is required");
            }

            if (creditRating.getCreatedBy() == null || creditRating.getCreatedBy().isBlank()) {
                  return ResponseEntity.badRequest().body("Credit rating created by is required");
            }

            CreditRating savedCreditRating = creditRatingService.createCreditRatings(creditRating);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCreditRating);
      }

      @PutMapping("/{id}")
      public ResponseEntity<?> updateCreditRating(@PathVariable Integer id, @RequestBody CreditRating creditRating) {
            Optional<CreditRating> existingCreditRating = creditRatingService.getCreditRatingById(id);

            if (existingCreditRating.isEmpty()) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body("Credit rating not found with id: " + id);
            }

            CreditRating updatedCreditRating = creditRatingService.updateCreditRating(id, creditRating);
            return ResponseEntity.ok(updatedCreditRating);
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteCreditRating(@PathVariable Integer id) {
            boolean deleted = creditRatingService.deleteCreditRating(id);

            if (deleted) {
                  return ResponseEntity.ok("Credit rating deleted successfully");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Credit rating not found with id: " + id);
      }
}