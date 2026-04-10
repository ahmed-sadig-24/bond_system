package com.skillstorm.bondsystem.service;

import com.skillstorm.bondsystem.model.Bond;
import com.skillstorm.bondsystem.model.BondType;
import com.skillstorm.bondsystem.repository.BondRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class BondService {

      private final BondRepository bondRepository;

      public BondService(BondRepository bondRepository) {
            this.bondRepository = bondRepository;
      }

      public List<Bond> getAllBonds() {
            return bondRepository.findAll();
      }

      public Optional<Bond> getBondById(Integer id) {
            return bondRepository.findById(id);
      }

      public Bond createBond(Bond bond) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            bond.setCreatedAt(now);
            bond.setUpdatedAt(now);

            return bondRepository.save(bond);
      }

      public Bond updateBond(Integer id, Bond bond) {
            Bond existingBond = bondRepository.findById(id).orElse(null);
            if (existingBond == null) {
                  throw new EntityNotFoundException("Bond not found with id: " + id);
            }

            existingBond.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            return bondRepository.save(existingBond);
      }

      public boolean deleteBond(Integer id) {
            Optional<Bond> existingBond = bondRepository.findById(id);

            if (existingBond.isPresent()) {
                  bondRepository.deleteById(id);
                  return true;
            }

            return false;
      }
}
