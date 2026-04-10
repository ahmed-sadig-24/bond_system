package com.skillstorm.bondsystem.controller;

import com.skillstorm.bondsystem.model.Bond;
import com.skillstorm.bondsystem.service.BondService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/bonds")
public class BondController {

      private final BondService bondService;

      public BondController(BondService bondService) {
            this.bondService = bondService;
      }

      @GetMapping
      public ResponseEntity<List<Bond>> getAllBonds() {
            List<Bond> bondList = bondService.getAllBonds();

            return ResponseEntity.ok(bondList);
      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getBondById(Integer id) {
            Optional<Bond> existingBond = bondService.getBondById(id);

            if (existingBond.isPresent()) {
                  return ResponseEntity.ok(existingBond.get());
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Credit rating not found with id: " + id);
      }

      @PostMapping
      public ResponseEntity<?> createBond(@RequestBody Bond bond) {

            if (bond.getBondId() == null) {
                  return ResponseEntity.badRequest().body("Bond id is required");
            }

            if (bond.getBondName() == null || bond.getBondName().isBlank()) {
                  return ResponseEntity.badRequest().body("Bond name is required");
            }

            if (bond.getCreatedBy() == null || bond.getCreatedBy().isBlank()) {
                  return ResponseEntity.badRequest().body("Bond created by is required");
            }

            Bond savedBond = bondService.createBond(bond);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBond);
      }

      @PutMapping("/{id}")
      public ResponseEntity<?> updateBond(@PathVariable Integer id, @RequestBody Bond bond) {
            Optional<Bond> existingBond = bondService.getBondById(id);

            if (existingBond.isEmpty()) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body("Bond not found with id: " + id);
            }

            Bond updatedBond = bondService.updateBond(id, bond);
            return ResponseEntity.ok(updatedBond);
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteBond(@PathVariable Integer id) {
            boolean deleted = bondService.deleteBond(id);

            if (deleted) {
                  return ResponseEntity.ok("Bond deleted successfully");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Bond not found with id: " + id);
      }
}
