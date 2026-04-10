package com.skillstorm.bondsystem.repository;

import com.skillstorm.bondsystem.model.CreditRating;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRatingRepository  extends JpaRepository<CreditRating, Integer> {
}
