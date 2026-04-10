package com.skillstorm.bondsystem.dto;

public class IssuerRequest {

      private Integer issuerId;
      private String issuerName;
      private Integer issuerTypeId;
      private Integer countryId;
      private Integer creditRatingId;
      private String createdBy;
      private String updatedBy;

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

      public Integer getCountryId() {
            return countryId;
      }

      public void setCountryId(Integer countryId) {
            this.countryId = countryId;
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

      public String getUpdatedBy() {
            return updatedBy;
      }

      public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
      }
}