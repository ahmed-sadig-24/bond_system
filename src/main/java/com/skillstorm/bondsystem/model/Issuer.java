package com.skillstorm.bondsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "issuer")
public class Issuer {

      @Id
      @Column(name = "issuer_id", nullable = false)
      private Integer issuerId;

      @Column(name = "issuer_name", nullable = false, length = 255)
      private String issuerName;

      @ManyToOne
      @JoinColumn(name = "issuer_type_id", nullable = false)
      //@JsonIgnoreProperties("issuers")
      //@JsonIgnore
      @JsonBackReference
      private IssuerType issuerType;

      @ManyToOne
      @JoinColumn(name = "country_id", nullable = false)
      //private Integer countryId;
      private Country country;

      @Column(name = "credit_rating_id", nullable = false)
      private Integer creditRatingId;

      @Column(name = "created_by", nullable = false, length = 50)
      private String createdBy;

      @Column(name = "created_at")
      private Timestamp createdAt;

      @Column(name = "updated_by")
      private String updatedBy;

      @Column(name = "updated_at")
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

      //public Integer getIssuerTypeId() {
        //    return issuerType;
      //}

      public IssuerType getIssuerType() {
            return issuerType;
      }

      public void setIssuerType(IssuerType issuerType) {
            this.issuerType = issuerType;
      }

      //public void setIssuerTypeId(IssuerType issuerTypeId) {
        //    this.issuerType = issuerTypeId;
      //}

      //public Integer getCountryId() {
        //    return countryId;
      //}

      public Country getCountry() {
            return country;
      }

      //public void setCountryId(Integer countryId) {
        //    this.countryId = countryId;
      //}

      public void setCountry(Country country) {
            this.country = country;
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
