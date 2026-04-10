package com.skillstorm.bondsystem.controller;

import com.skillstorm.bondsystem.dto.IssuerRequest;
import com.skillstorm.bondsystem.dto.IssuerResponse;
import com.skillstorm.bondsystem.model.Issuer;
import com.skillstorm.bondsystem.service.IssuerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:5500")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/issuers")
public class IssuerController {

      private final IssuerService issuerService;

      public IssuerController(IssuerService issuerService) {
            this.issuerService = issuerService;
      }

      @GetMapping
      public ResponseEntity<?> getAllIssuers() {
            try {
                  List<IssuerResponse> issuerList = issuerService.getAllIssuers();

                  return ResponseEntity.ok(issuerList);
            } catch (RuntimeException e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }
      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getIssuerById(@PathVariable Integer id) {
            try {
                  IssuerResponse existingIssuer = issuerService.getIssuerById(id);

                  if (existingIssuer != null) {
                        return ResponseEntity.ok(existingIssuer);
                  }

                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body("Issuer not found with id: " + id);
            } catch (RuntimeException e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }

      }

      @PostMapping
      public ResponseEntity<?> createIssuer(@RequestBody IssuerRequest request) {
            try {
                  if (request.getIssuerId() == null) {
                        return ResponseEntity.badRequest().body("Issuer id is required");
                  }

                  if (request.getIssuerName() == null || request.getIssuerName().isBlank()) {
                        return ResponseEntity.badRequest().body("Issuer name is required");
                  }

                  if (request.getCreatedBy() == null || request.getCreatedBy().isBlank()) {
                        return ResponseEntity.badRequest().body("Issuer created by is required");
                  }

                  IssuerResponse savedIssuer = issuerService.createIssuer(request);

                  return ResponseEntity.status(HttpStatus.CREATED).body(savedIssuer);
            } catch (IllegalArgumentException e) {
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                          .body(e.getMessage());
            } catch (EntityNotFoundException e) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body(e.getMessage());
            } catch (RuntimeException e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }

      }

      @PutMapping("/{id}")
      public ResponseEntity<?> updateIssuer(@PathVariable Integer id, @RequestBody IssuerRequest issuer) {
            try {
                  Optional<IssuerResponse> existingIssuer = Optional.ofNullable(issuerService.getIssuerById(id));

                  if (existingIssuer.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Issuer not found with id: " + id);
                  }

                  IssuerResponse updatedIssuer = issuerService.updateIssuer(id, issuer);
                  return ResponseEntity.ok(updatedIssuer);
            } catch (IllegalArgumentException e) {
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                          .body(e.getMessage());
            } catch (EntityNotFoundException e) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body(e.getMessage());
            } catch (RuntimeException e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }

      }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteIssuer(@PathVariable Integer id) {
            try {
                  boolean deleted = issuerService.deleteIssuer(id);

                  if (deleted) {
                        return ResponseEntity.ok("Issuer deleted successfully");
                  }

                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body("Issuer not found with id: " + id);
            } catch (RuntimeException e) {
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                          .body("Oooops, something went wrong");
            }
      }

}
