package com.skillstorm.bondsystem.service;

import com.skillstorm.bondsystem.model.BondType;
import com.skillstorm.bondsystem.repository.BondTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class BondTypeService {

      private final BondTypeRepository bondTypeRepository;

      public BondTypeService(BondTypeRepository bondTypeRepository) {
            this.bondTypeRepository = bondTypeRepository;
      }

      public List<BondType> getAllBondTypes() {
            try {
                  return bondTypeRepository.findAll();
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to fetch bond types from database", e);
            }
      }

      public Optional<BondType> getBondTypeById(Integer id) {
       try {
            return bondTypeRepository.findById(id);
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to fetch bond types from database", e);
            }
      }

      public BondType createBondType(BondType bondType) {
            try {
                  if (bondType.getBondTypeId() == null) {
                        throw new IllegalArgumentException("Bond type is required");
                  }
                  Timestamp now= new Timestamp(System.currentTimeMillis());
                  bondType.setCreatedAt(now);
                  bondType.setUpdatedAt(now);

                  return bondTypeRepository.save(bondType);
            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Invalid bond type data or foreign key violation", e);
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to create bond type", e);
            }

      }

      public BondType updateBondType(Integer id, BondType bondType) {
            try {
                  BondType existingBondType = bondTypeRepository.findById(id)
                          .orElseThrow(() -> new EntityNotFoundException("Bond type not found with id: " + id ));

                  if (existingBondType == null) {
                        throw new EntityNotFoundException("Bond type not found with id: " + id);
                  }

                  existingBondType.setBondTypeName(bondType.getBondTypeName());
                  //existingBondType.setCreatedBy(bondType.getCreatedBy());
                  existingBondType.setUpdatedBy(bondType.getUpdatedBy());
                  existingBondType.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                  return bondTypeRepository.save(existingBondType);

            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Invalid bond type update or foreign key violation, e");
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to update bond type with id: " + id, e);
            }

      }

      public boolean deleteBondType(Integer id) {
            try {
                  Optional<BondType> existingBondType = bondTypeRepository.findById(id);

                  if (existingBondType.isPresent()) {
                        bondTypeRepository.deleteById(id);
                        return true;
                  }

                  return false;
            } catch (DataIntegrityViolationException e) {
                  throw new RuntimeException("Cannot delete bond type because it is referenced by other records", e);
            } catch (DataAccessException e) {
                  throw new RuntimeException("Failed to delete bond type with id: " + id, e);
            }
      }
}
