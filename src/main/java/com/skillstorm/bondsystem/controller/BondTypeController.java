package com.skillstorm.bondsystem.controller;

import com.skillstorm.bondsystem.model.BondType;
import com.skillstorm.bondsystem.service.BondTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/bond_types")
public class BondTypeController {

      private final BondTypeService bondTypeService;

      public BondTypeController(BondTypeService bondTypeService) {
            this.bondTypeService = bondTypeService;
      }

      @GetMapping
      public ResponseEntity<List<BondType>> getAllBondTypes() {
            List<BondType> bondTypeList = bondTypeService.getAllBondTypes();

            return ResponseEntity.ok(bondTypeList);
      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getBondTypeById(@PathVariable Integer id) {
            Optional<BondType> bondType = bondTypeService.getBondTypeById(id);

            if (bondType.isPresent()) {
                  return ResponseEntity.ok(bondType);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Bond type not found with id: " + id);
      }

      @PostMapping
      public ResponseEntity<?> createBondType(@RequestBody BondType bondType) {

            if (bondType.getBondTypeId() == null) {
                  return ResponseEntity.badRequest()
                          .body("Bond type id is required");
            }
            if (bondType.getBondTypeName() == null || bondType.getBondTypeName().isEmpty()) {
                  return ResponseEntity.badRequest()
                          .body("Bond type name is required");
            }

            if (bondType.getCreatedBy() == null || bondType.getCreatedBy().isEmpty()) {
                  return ResponseEntity.badRequest()
                          .body("Bond created by is required");
            }

            bondType.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            BondType savedBondType =  bondTypeService.createBondType(bondType);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedBondType);
      }

      @PutMapping("/{id}")
      public ResponseEntity<?> updateBondType(@PathVariable Integer id, @RequestBody BondType bondType) {
            Optional<BondType> existingBondType = bondTypeService.getBondTypeById(id);

            if (existingBondType.isEmpty()) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body("Bond type not found with id: " +  id);
            }

            BondType updatedBondType = bondTypeService.updateBondType(id, bondType);
            return ResponseEntity.ok(updatedBondType);
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteBondType(@PathVariable Integer id) {

            boolean deleted = bondTypeService.deleteBondType(id);
            if (deleted) {
                  return ResponseEntity.ok("Bond type deleted successfully");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Bond type not found with id: " + id);
      }
}
