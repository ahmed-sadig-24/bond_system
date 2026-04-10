package com.skillstorm.bondsystem.controller;

import com.skillstorm.bondsystem.model.IssuerType;
import com.skillstorm.bondsystem.service.IssuerTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/issuer_types")
public class IssuerTypeController {

      private final IssuerTypeService issuerTypeService;

      public IssuerTypeController(IssuerTypeService issuerTypeService) {
            this.issuerTypeService = issuerTypeService;
      }

      @GetMapping
      public ResponseEntity<?> getAllIssuerTypes() {
            try {
                  List<IssuerType> issuerTypes = issuerTypeService.getAllIssuerTypes();

                  return ResponseEntity.ok(issuerTypes);
            } catch (RuntimeException e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }
      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getIssuerTypeById(@PathVariable Integer id) {
            try {
                  Optional<IssuerType> issuerType = issuerTypeService.getIssuerTypeById(id);


                  if (issuerType.isPresent()) {
                        return ResponseEntity.ok(issuerType);
                  }

                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body("Issuer type not found");

            } catch (RuntimeException e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }
      }

      @PostMapping
      public ResponseEntity<?> createIssuerType(@RequestBody IssuerType issuerType) {

            LocalDateTime currentDateTime = LocalDateTime.now();

            if (issuerType.getCreatedAt() == null) {
                  issuerType.setCreatedAt(Timestamp.valueOf(currentDateTime));
            }
            IssuerType savedIssuerType = issuerTypeService.createIssuerType(issuerType);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedIssuerType);
      }

      @PutMapping("/{id}")
      public ResponseEntity<?> updateIssuerType(@PathVariable Integer id, @RequestBody IssuerType issuerType) {
            Optional<IssuerType> existingIssuerType = issuerTypeService.getIssuerTypeById(id);
            if (existingIssuerType.isEmpty()) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body("Issuer type not found with id: " + id);
            }

            return ResponseEntity.ok(issuerType);
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteIssuerType(@PathVariable Integer id) {
            boolean deleted = issuerTypeService.deleteIssuerType(id);

            if (deleted) {
                  return ResponseEntity.ok("Issuer type deleted successfully");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Issuer type not found with id " + id);
      }
}
