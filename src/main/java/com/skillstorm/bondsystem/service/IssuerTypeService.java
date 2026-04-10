package com.skillstorm.bondsystem.service;

import com.skillstorm.bondsystem.model.IssuerType;
import com.skillstorm.bondsystem.repository.IssuerTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class IssuerTypeService {

      private final IssuerTypeRepository issuerTypeRepository;

      public IssuerTypeService(IssuerTypeRepository issuerTypeRepository) {
            this.issuerTypeRepository = issuerTypeRepository;
      }

      public List<IssuerType> getAllIssuerTypes() {
            try {
                  return issuerTypeRepository.findAll();
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to fetch issuer types from database", e);
            }
      }

      public Optional<IssuerType> getIssuerTypeById(Integer id) {
            try {
                  return issuerTypeRepository.findById(id);

            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to fetch issuer type with id: " + id, e);
            }
      }

      public IssuerType createIssuerType(IssuerType issuerType) {
            try {
                  if (issuerType.getIssuerTypeId() == null) {
                        throw new IllegalArgumentException("Issuer type is required");
                  }

                  Timestamp now = new Timestamp(System.currentTimeMillis());
                  issuerType.setCreatedAt(now);
                  issuerType.setUpdatedAt(now);

                  return issuerTypeRepository.save(issuerType);

            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Invalid issuer type data or foreign key violation", e);
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to create issuer type", e);
            }
      }

      public IssuerType updateIssuerType(Integer id, IssuerType issuerType) {
            try {
                  IssuerType existingIssuerType = issuerTypeRepository.findById(id)
                          .orElseThrow(() -> new EntityNotFoundException("Issuer type not found with id: " + id ));


                  existingIssuerType.setIssuerTypeName(issuerType.getIssuerTypeName());
                  existingIssuerType.setUpdatedBy(issuerType.getUpdatedBy());
                  existingIssuerType.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                  return issuerTypeRepository.save(existingIssuerType);

            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Invalid issuer type update or foreign key violation, e");
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to update issuer type with id: " + id, e);
            }
      }

      public boolean deleteIssuerType(Integer id) {
            try {
                  Optional<IssuerType> existingIssuerType = issuerTypeRepository.findById(id);

                  if (existingIssuerType.isPresent()) {
                        issuerTypeRepository.deleteById(id);
                        return true;
                  }

                  return false;
            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Cannot delete issuer type because it is referenced by other records", e);
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to delete issuer type with id: " +  id, e);
            }
      }
}
