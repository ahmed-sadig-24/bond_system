package com.skillstorm.bondsystem.controller;

import com.skillstorm.bondsystem.model.Country;
import com.skillstorm.bondsystem.model.Issuer;
import com.skillstorm.bondsystem.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

      private final CountryService countryService;

      public CountryController(CountryService countryService) {
            this.countryService = countryService;
      }

      @GetMapping
      public ResponseEntity<?> getAllCountries() {
            try {
            List<Country> countries = countryService.getAllCountries();

            return ResponseEntity.ok(countries);
            } catch (RuntimeException e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }
      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getCountryById(@PathVariable Integer id) {
            try {
                  Optional<Country> country = countryService.getCountryById(id);


                  if (country.isPresent()) {
                        return ResponseEntity.ok(country);
                  }

                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body("Country not found");

            } catch (RuntimeException e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }
      }

      @PostMapping
      public ResponseEntity<Country> createCountry(@RequestBody Country country) {
            //Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            LocalDateTime currentDateTime = LocalDateTime.now();

            if (country.getCreatedAt() == null) {
                  country.setCreatedAt(Timestamp.valueOf(currentDateTime));
            }
            Country savedCountry = countryService.createCountry(country);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCountry);
      }

      @PutMapping("/{id}")
      public ResponseEntity<?> updateCountry(@PathVariable Integer id, @RequestBody Country country) {
            try {
                  Optional<Country> existingCountry = countryService.getCountryById(id);
                  if (existingCountry.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Country not found with id " + id);
                  }

                  Country updatedCountry = countryService.updateCountry(id, country);

                  return ResponseEntity.ok(updatedCountry);
            } catch (IllegalArgumentException e) {
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                          .body(e.getMessage());
            } catch (EntityNotFoundException e) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body(e.getMessage());
            } catch (RuntimeException e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }

      }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteCountry(@PathVariable Integer id) {
            boolean deleted = countryService.deleteCountry(id);

            if (deleted) {
                  return ResponseEntity.ok("Country deleted successfully");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Country not found with id " + id);
      }

}
