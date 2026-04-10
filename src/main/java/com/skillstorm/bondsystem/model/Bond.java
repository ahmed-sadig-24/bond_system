package com.skillstorm.bondsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "bond")
public class Bond {

      @Id
      @Column(name = "bond_id", nullable = false, unique = true)
      private Integer bondId;

      @Column(name = "bond_name", nullable = false, unique = true, length = 255)
      private String bondName;

      @Column(name = "isisn", nullable = false)
      private String isin;

      @Column(name = "bond_type_id", nullable = false)
      private Integer bondTypeId;

      @Column(name = "face_value")
      private Integer faceValue;

      @Column(name = "coupon_rate")
      private Integer couponRate;

      @Column(name = "maturity_date")
      private Date maturityDate;

      @Column(name = "bond_status")
      private Character BondStatus;

      @Column(name = "created_by", nullable = false, length = 50)
      private String createdBy;

      @Column(name = "created_at")
      private Timestamp createdAt;

      @Column(name = "updated_by", length = 50)
      private String updatedBy;

      @Column(name = "updated_at")
      private Timestamp updatedAt;

      public Integer getBondId() {
            return bondId;
      }

      public void setBondId(Integer bondId) {
            this.bondId = bondId;
      }

      public String getBondName() {
            return bondName;
      }

      public void setBondName(String bondName) {
            this.bondName = bondName;
      }

      public String getIsin() {
            return isin;
      }

      public void setIsin(String isin) {
            this.isin = isin;
      }

      public Integer getBondTypeId() {
            return bondTypeId;
      }

      public void setBondTypeId(Integer bondTypeId) {
            this.bondTypeId = bondTypeId;
      }

      public Integer getFaceValue() {
            return faceValue;
      }

      public void setFaceValue(Integer faceValue) {
            this.faceValue = faceValue;
      }

      public Integer getCouponRate() {
            return couponRate;
      }

      public void setCouponRate(Integer couponRate) {
            this.couponRate = couponRate;
      }

      public Date getMaturityDate() {
            return maturityDate;
      }

      public void setMaturityDate(Date maturityDate) {
            this.maturityDate = maturityDate;
      }

      public Character getBondStatus() {
            return BondStatus;
      }

      public void setBondStatus(Character bondStatus) {
            BondStatus = bondStatus;
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
