package com.example.DTO.Project.mapper;

import com.example.DTO.Project.dto.PatientRequestDTO;
import com.example.DTO.Project.dto.PatientResponseDTO;
import com.example.DTO.Project.entity.Patient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MANUAL MAPPING: the same four methods as PatientMapper, written by hand.
 * Demo: in PatientService, change "PatientMapper" to "PatientManualMapper"
 * (field + constructor + import) and the app works exactly the same.
 */
@Component
public class PatientManualMapper {

    // Request DTO -> new Entity
    public Patient toEntity(PatientRequestDTO request) {
        Patient patient = new Patient();
        patient.setFullName(request.fullName());
        patient.setEmail(request.email());
        patient.setPhone(request.phone());
        patient.setAge(request.age());
        patient.setNationalId(request.nationalId());
        patient.setMedicalNotes(request.medicalNotes());
        return patient;                       // id stays null: the database fills it
    }

    // Entity -> Response DTO (nationalId and medicalNotes are simply not passed)
    public PatientResponseDTO toResponseDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getFullName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getAge()
        );
    }

    // List of entities -> list of DTOs
    public List<PatientResponseDTO> toResponseDTOList(List<Patient> patients) {
        return patients.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Request DTO -> EXISTING Entity (for update)
    public void updateEntity(PatientRequestDTO request, Patient patient) {
        patient.setFullName(request.fullName());
        patient.setEmail(request.email());
        patient.setPhone(request.phone());
        patient.setAge(request.age());
        patient.setNationalId(request.nationalId());
        patient.setMedicalNotes(request.medicalNotes());
    }
}
