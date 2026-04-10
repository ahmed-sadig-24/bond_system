package com.skillstorm.bondsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "issuance")
public class Issuance {

      @Id
      @Column(name = "issuance", nullable = false, unique = true)
      private Integer issuanceId;

      @Column(name = "issuer_role", nullable = false, length = 50)
      private String issuerRole;

      @Column(name = "issuance_size")
      @Min(1)
      private Integer issuanceSize;

      @Column(name = "issuance_date", nullable = false)
      private Date issuanceDate;

      @Column(name = "created_by", nullable = false, length = 50)
      private String createdBy;

      @Column(name = "created_at", nullable = false)
      private Timestamp createdAt;

      @Column(name = "updated_by")
      private String updatedBy;

      //private Timestamp

}
