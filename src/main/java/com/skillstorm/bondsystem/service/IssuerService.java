package com.skillstorm.bondsystem.service;

import com.skillstorm.bondsystem.dto.IssuerRequest;
import com.skillstorm.bondsystem.dto.IssuerResponse;
import com.skillstorm.bondsystem.model.Country;
import com.skillstorm.bondsystem.model.Issuer;
import com.skillstorm.bondsystem.model.IssuerType;
import com.skillstorm.bondsystem.repository.CountryRepository;
import com.skillstorm.bondsystem.repository.IssuerRepository;
import com.skillstorm.bondsystem.repository.IssuerTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IssuerService {

      private final IssuerRepository issuerRepository;
      private final IssuerTypeRepository issuerTypeRepository;
      private final CountryRepository countryRepository;

      public IssuerService(IssuerRepository issuerRepository, IssuerTypeRepository issuerTypeRepository, CountryRepository countryRepository) {
            this.issuerRepository = issuerRepository;
            this.issuerTypeRepository = issuerTypeRepository;
            this.countryRepository = countryRepository;
      }

      public List<IssuerResponse> getAllIssuers() {
            try {
                  return issuerRepository.findAll()
                          .stream()
                          .map(this::mapToResponse)
                          .collect(Collectors.toList());
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to fetch issuers from database", e);
            }
      }

      public IssuerResponse getIssuerById(Integer id) {
            try {
                  Issuer issuer =  issuerRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Issuer not found with id: " + id));

                  return mapToResponse(issuer);

            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to fetch issuer with id: " + id, e);
            }

      }

      public IssuerResponse createIssuer(IssuerRequest request) {
            try {
                  if (issuerRepository.existsById(request.getIssuerId())) {
                        throw new RuntimeException("Issuer already exists with id: " + request.getIssuerId());
                  }

                  IssuerType issuerType = issuerTypeRepository.findById(request.getIssuerTypeId())
                          .orElseThrow(() -> new RuntimeException("Issuer Type not found with id: " + request.getIssuerTypeId()));

                  Country country = countryRepository.findById(request.getCountryId())
                          .orElseThrow(() -> new RuntimeException("Country not found with id: " + request.getCountryId()));

                  Issuer issuer = new Issuer();
                  issuer.setIssuerId(request.getIssuerId());
                  issuer.setIssuerName(request.getIssuerName());
                  issuer.setIssuerType(issuerType);
                  issuer.setCountry(country);
                  issuer.setCreditRatingId(request.getCreditRatingId());
                  issuer.setCreatedBy(request.getCreatedBy());
                  issuer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                  issuer.setUpdatedBy(request.getUpdatedBy());
                  issuer.setUpdatedAt(null);

                  return mapToResponse(issuerRepository.save(issuer));

            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Invalid issuer data or foreign key violation", e);
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to create issuer", e);
            }

      }

      public IssuerResponse updateIssuer(Integer id, IssuerRequest request) {
            try {
                  Issuer issuer = issuerRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Issuer not found with id: " + id));

                  IssuerType issuerType = issuerTypeRepository.findById(request.getIssuerTypeId())
                          .orElseThrow(() -> new RuntimeException("Issuer Type not found with id: " + request.getIssuerTypeId()));

                  Country country = countryRepository.findById(request.getCountryId())
                          .orElseThrow(() -> new RuntimeException("Country not found with id: " + request.getCountryId()));

                  issuer.setIssuerName(request.getIssuerName());
                  issuer.setIssuerType(issuerType);
                  issuer.setCountry(country);
                  issuer.setCreditRatingId(request.getCreditRatingId());
                  issuer.setUpdatedBy(request.getUpdatedBy());
                  issuer.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                  return mapToResponse(issuerRepository.save(issuer));

            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Invalid issuer update or foreign key violation, e");
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to update issuer with id: " + id, e);
            }
      }

      public boolean deleteIssuer(Integer id) {
            try {
                  Optional<Issuer> existingIssuer = issuerRepository.findById(id);

                  if (existingIssuer.isPresent()) {
                        issuerRepository.deleteById(id);
                        return true;
                  }

                  return false;
            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Cannot delete issuer because it is referenced by other records", e);
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to delete issuer with id: " +  id, e);
            }
      }

      private IssuerResponse mapToResponse(Issuer issuer) {

            IssuerResponse response = new IssuerResponse();

            response.setIssuerId(issuer.getIssuerId());
            response.setIssuerName(issuer.getIssuerName());

            if (issuer.getIssuerType() != null) {
                  response.setIssuerTypeId(issuer.getIssuerType().getIssuerTypeId());
                  response.setIssuerTypeName(issuer.getIssuerType().getIssuerTypeName());
            }

            if (issuer.getCountry() != null) {
                  response.setCountryId(issuer.getCountry().getCountryId());
                  response.setCountryName(issuer.getCountry().getCountryName());
            }

            response.setCreditRatingId(issuer.getCreditRatingId());
            response.setCreatedBy(issuer.getCreatedBy());
            response.setCreatedAt(issuer.getCreatedAt());
            response.setUpdatedBy(issuer.getUpdatedBy());
            response.setUpdatedAt(issuer.getUpdatedAt());

            return response;
      }
}
