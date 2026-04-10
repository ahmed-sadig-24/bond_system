package com.skillstorm.bondsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "bond_type")
public class BondType {

      @Id
      @Column(name = "bond_type_id")
      private Integer bondTypeId;

      @Column(name = "bond_type_name", nullable = false, length = 255, unique = true)
      private String bondTypeName;

      @Column(name = "created_by", nullable = false, length = 50)
      private String createdBy;

      @Column(name = "created_at")
      private Timestamp createdAt;

      @Column(name = "updated_by", length = 50)
      private String updatedBy;

      @Column(name = "updated_at")
      private Timestamp updatedAt;


      public Integer getBondTypeId() {
            return bondTypeId;
      }

      public void setBondTypeId(Integer bondTypeId) {
            this.bondTypeId = bondTypeId;
      }

      public String getBondTypeName() {
            return bondTypeName;
      }

      public void setBondTypeName(String bondTypeName) {
            this.bondTypeName = bondTypeName;
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
