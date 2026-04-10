package com.skillstorm.bondsystem.service;

import com.skillstorm.bondsystem.model.Country;
import com.skillstorm.bondsystem.model.Issuer;
import com.skillstorm.bondsystem.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

      private final CountryRepository countryRepository;

      public CountryService(CountryRepository countryRepository) {
            this.countryRepository = countryRepository;
      }

      public List<Country> getAllCountries() {
             try {
                  return countryRepository.findAll();
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to fetch countries from database", e);
            }
      }

      public Optional<Country> getCountryById(Integer id) {
             try {
                  return countryRepository.findById(id);

            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to fetch country with id: " + id, e);
            }
      }

      public Country createCountry(Country country) {
            try {
                  if (country.getCountryId() == null) {
                        throw new IllegalArgumentException("Country is required");
                  }

                  Timestamp now = new Timestamp(System.currentTimeMillis());
                  country.setCreatedAt(now);
                  country.setUpdatedAt(now);

                  return countryRepository.save(country);

            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Invalid country data or foreign key violation", e);
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to create country", e);
            }
      }

      public Country updateCountry(Integer id, Country country) { // Update country by ID
            try {
                  Country existingCountry = countryRepository.findById(id)
                          .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id ));


                  existingCountry.setCountryName(country.getCountryName());
                  existingCountry.setUpdatedBy(country.getUpdatedBy());
                  existingCountry.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                  return countryRepository.save(existingCountry);

            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Invalid country update or foreign key violation, e");
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to update country with id: " + id, e);
            }
      }

      public boolean deleteCountry(Integer id) {
            try {
                  Optional<Country> existingCountry = countryRepository.findById(id);

                  if (existingCountry.isPresent()) {
                        countryRepository.deleteById(id);
                        return true;
                  }

                  return false;
            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Cannot delete country because it is referenced by other records", e);
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to delete country with id: " +  id, e);
            }
      }
}
