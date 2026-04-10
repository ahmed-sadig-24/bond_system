package com.skillstorm.bondsystem.repository;

import com.skillstorm.bondsystem.model.BondType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondTypeRepository extends JpaRepository<BondType, Integer> {
}
