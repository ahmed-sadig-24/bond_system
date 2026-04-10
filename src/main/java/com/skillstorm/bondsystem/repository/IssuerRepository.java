package com.skillstorm.bondsystem.repository;

import com.skillstorm.bondsystem.model.Issuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerRepository extends JpaRepository<Issuer, Integer> {
}
