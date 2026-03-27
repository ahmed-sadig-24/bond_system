package com.skillstorm.bondsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "country")
public class Country {

      @Id
      @Column(name = "country_id")
      private Integer countryId;

      @Column(name = "country_name", nullable = false, unique = true, length = 255)
      private String countryName;

      @Column(name = "created_by", nullable = false, length = 50)
      private String createdBy;

      @Column(name = "created_at", nullable = false)
      private Timestamp createdAt;

      @Column(name = "updated_by", length = 50)
      private String updatedBy;

      @Column(name = "updated_at")
      private Timestamp updatedAt;

      public Country() {
      }

      public Country(Integer countryId, String countryName, String createdBy, Timestamp createdAt, String updatedBy, Timestamp updatedAt) {
            this.countryId = countryId;
            this.countryName = countryName;
            this.createdBy = createdBy;
            this.createdAt = createdAt;
            this.updatedBy = updatedBy;
            this.updatedAt = updatedAt;
      }

      public Integer getCountryId() {
            return countryId;
      }

      public void setCountryId(Integer countryId) {
            this.countryId = countryId;
      }

      public String getCountryName() {
            return countryName;
      }

      public void setCountryName(String countryName) {
            this.countryName = countryName;
      }

      public String getCreatedBy() {
            return createdBy;
      }

      public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
      }

      public void setCreatedAt(Timestamp createdAt) {
            this.createdAt = createdAt;
      }

      public String getUpdatedBy() {
            return updatedBy;
      }

      public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
      }

      public void setUpdatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
      }
}
