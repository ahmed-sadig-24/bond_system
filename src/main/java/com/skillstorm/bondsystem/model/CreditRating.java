package com.skillstorm.bondsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "credit_rating")
public class CreditRating {

      @Id
      @Column(name = "credit_rating_id", nullable = false)
      private Integer creditRatingId;

      @Column(name = "credit_rating_name", nullable = false, unique = true, length = 255)
      private String creditRatingName;

      @Column(name = "created_by", nullable = false, length = 50)
      private String createdBy;

      @Column(name = "created_at", nullable = false)
      private Timestamp createdAt;

      @Column(name = "updated_by", length = 50)
      private String updatedBy;

      @Column(name = "updated_at")
      private Timestamp updatedAt;

      public Integer getCreditRatingId() {
            return creditRatingId;
      }

      public void setCreditRatingId(Integer creditRatingId) {
            this.creditRatingId = creditRatingId;
      }

      public String getCreditRatingName() {
            return creditRatingName;
      }

      public void setCreditRatingName(String creditRatingName) {
            this.creditRatingName = creditRatingName;
      }

      public String getCreatedBy() {
            return createdBy;
      }

      public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
      }

      public Timestamp getCreatedAt() {
            return createdAt;
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

      public Timestamp getUpdatedAt() {
            return updatedAt;
      }

      public void setUpdatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
      }
}
