package com.skillstorm.bondsystem.repository;

import com.skillstorm.bondsystem.model.Bond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondRepository extends JpaRepository<Bond, Integer> {
}
