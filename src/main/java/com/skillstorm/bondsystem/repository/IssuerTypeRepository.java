package com.skillstorm.bondsystem.repository;

import com.skillstorm.bondsystem.model.IssuerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerTypeRepository extends JpaRepository<IssuerType, Integer> {
}
