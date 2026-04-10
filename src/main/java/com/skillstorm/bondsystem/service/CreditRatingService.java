package com.skillstorm.bondsystem.service;

import com.skillstorm.bondsystem.model.CreditRating;
import com.skillstorm.bondsystem.repository.CreditRatingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CreditRatingService {

      private final CreditRatingRepository creditRatingRepository;

      public CreditRatingService(CreditRatingRepository creditRatingRepository) {
            this.creditRatingRepository = creditRatingRepository;
      }

      public List<CreditRating> getAllCreditRating() {
            return creditRatingRepository.findAll();
      }

      public Optional<CreditRating> getCreditRatingById(Integer id) {
            return creditRatingRepository.findById(id);
      }

      public CreditRating createCreditRatings(CreditRating creditRating) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            creditRating.setCreatedAt(now);
            creditRating.setUpdatedAt(now);

            return creditRatingRepository.save(creditRating);
      }

      public CreditRating updateCreditRating(Integer id, CreditRating creditRating) {
            CreditRating existingCreditRating = creditRatingRepository.findById(id).orElse(null);
            if (existingCreditRating == null) {
                  throw new EntityNotFoundException("Credit rating not found with id: " + id);
            }

            existingCreditRating.setCreditRatingName(creditRating.getCreditRatingName());
            //existingCreditRating.setUpdatedBy(creditRating.getUpdatedBy());
            existingCreditRating.setUpdatedBy("ahmed");
            existingCreditRating.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            return creditRatingRepository.save(existingCreditRating);
      }

      public boolean deleteCreditRating(Integer id) {
            Optional<CreditRating> existingCreditRating = creditRatingRepository.findById(id);

            if (existingCreditRating.isPresent()) {
                  creditRatingRepository.deleteById(id);
                  return true;
            }

            return false;
      }
}
