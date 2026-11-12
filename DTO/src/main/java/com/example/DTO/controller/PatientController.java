package com.example.DTO.Project.controller;

import com.example.DTO.Project.dto.PatientRequestDTO;
import com.example.DTO.Project.dto.PatientResponseDTO;
import com.example.DTO.Project.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CONTROLLER: HTTP only. Receives DTOs, returns DTOs.
 * No logic here: everything is delegated to the service.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    // Constructor injection
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // CREATE: 201 Created, or 400 if the data is invalid
    @PostMapping
    public ResponseEntity<PatientResponseDTO> registerPatient(
            @Valid @RequestBody PatientRequestDTO request) {
        PatientResponseDTO created = patientService.registerPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ ALL: 200 OK
    @GetMapping
    public List<PatientResponseDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    // READ ONE: 200 OK, or 404 if the id does not exist
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.of(patientService.getPatientById(id));
    }

    // UPDATE: 200 OK, 400 if invalid, 404 if the id does not exist
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientRequestDTO request) {
        return ResponseEntity.of(patientService.updatePatient(id, request));
    }

    // DELETE: 204 No Content, or 404 if the id does not exist
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        boolean deleted = patientService.deletePatient(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}