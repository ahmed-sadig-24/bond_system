package com.skillstorm.bondsystem.service;

import com.skillstorm.bondsystem.model.Country;
import com.skillstorm.bondsystem.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

      private final CountryRepository countryRepository;

      public CountryService(CountryRepository countryRepository) {
            this.countryRepository = countryRepository;
      }

      public List<Country> getAllCountries() {
            return countryRepository.findAll();
      }

      public Country getCountryById(Integer id) {
            Optional<Country> optionalCountry = countryRepository.findById(id);
            return optionalCountry.orElse(null);
      }

      public Country createCountry(Country country) {
            return countryRepository.save(country);
      }

      public Country updateCountry(Integer id, Country country) {
            Optional<Country> optionalCountry = countryRepository.findById(id);

            if (optionalCountry.isPresent()) {
                  Country existingCountry = optionalCountry.get();

                  return countryRepository.save(country);
            }

            return null;
      }

      public boolean deleteCountry(Integer id) {
            Optional<Country> optionalCountry = countryRepository.findById(id);

            if (optionalCountry.isPresent()) {
                  countryRepository.deleteById(id);
                  return true;
            }

            return false;
      }
}
