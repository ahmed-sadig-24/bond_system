package com.skillstorm.bondsystem.dto;

import java.sql.Timestamp;

public class IssuerResponse {

      private Integer issuerId;
      private String issuerName;

      private Integer issuerTypeId;
      private String issuerTypeName;

      private Integer countryId;
      private String countryName;

      private Integer creditRatingId;

      private String createdBy;
      private Timestamp createdAt;
      private String updatedBy;
      private Timestamp updatedAt;

      public Integer getIssuerId() {
            return issuerId;
      }

      public void setIssuerId(Integer issuerId) {
            this.issuerId = issuerId;
      }

      public String getIssuerName() {
            return issuerName;
      }

      public void setIssuerName(String issuerName) {
            this.issuerName = issuerName;
      }

      public Integer getIssuerTypeId() {
            return issuerTypeId;
      }

      public void setIssuerTypeId(Integer issuerTypeId) {
            this.issuerTypeId = issuerTypeId;
      }

      public String getIssuerTypeName() {
            return issuerTypeName;
      }

      public void setIssuerTypeName(String issuerTypeName) {
            this.issuerTypeName = issuerTypeName;
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

      public Integer getCreditRatingId() {
            return creditRatingId;
      }

      public void setCreditRatingId(Integer creditRatingId) {
            this.creditRatingId = creditRatingId;
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