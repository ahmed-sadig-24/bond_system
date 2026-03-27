package com.skillstorm.bondsystem.controller;

import com.skillstorm.bondsystem.model.Country;
import com.skillstorm.bondsystem.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

      private final CountryService countryService;

      public CountryController(CountryService countryService) {
            this.countryService = countryService;
      }

      @GetMapping
      public ResponseEntity<List<Country>> getAllCountries() {
            List<Country> countries = countryService.getAllCountries();

            return ResponseEntity.ok(countries);
      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getCountryById(@PathVariable Integer id) {
            Country country = countryService.getCountryById(id);

            if (country != null) {
                  return ResponseEntity.ok(country);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Country not found");
      }
}
