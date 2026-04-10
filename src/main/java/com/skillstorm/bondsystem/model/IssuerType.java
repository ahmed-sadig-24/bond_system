package com.skillstorm.bondsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "issuer_type")
public class IssuerType {

      @Id
      @Column(name = "issuer_type_id")
      private Integer issuerTypeId;

      @Column(name = "issuer_type_name", nullable = false, unique = true, length = 255)
      private String issuerTypeName;

      @Column(name = "created_by", length = 50)
      private String createdBy;

      @Column(name = "created_at")
      private Timestamp createdAt;

      @Column(name = "updated_by", length = 50)
      private String updatedBy;

      @Column(name = "updated_at")
      private Timestamp updatedAt;

      @OneToMany(mappedBy = "issuerType")
      @JsonIgnore
      //@JsonManagedReference
      private List<Issuer> issuers;

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

      public List<Issuer> getIssuers() {
            return issuers;
      }

      public void setIssuers(List<Issuer> issuers) {
            this.issuers = issuers;
      }
}
