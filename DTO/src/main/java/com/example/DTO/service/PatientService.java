package com.example.DTO.Project.service;

import com.example.DTO.Project.dto.PatientRequestDTO;
import com.example.DTO.Project.dto.PatientResponseDTO;
import com.example.DTO.Project.entity.Patient;
import com.example.DTO.Project.mapper.PatientMapper;
import com.example.DTO.Project.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * SERVICE: the logic lives here.
 * DTOs come in, DTOs go out. Entities never leave this class.
 */
@Service
public class  PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    // Constructor injection: Spring creates both objects and passes them in
    public PatientService(PatientRepository patientRepository,
                          PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    // CREATE
    public PatientResponseDTO registerPatient(PatientRequestDTO request) {
        Patient patient = patientMapper.toEntity(request);        // 1. DTO -> Entity
        Patient savedPatient = patientRepository.save(patient);   // 2. save to DB
        return patientMapper.toResponseDTO(savedPatient);         // 3. Entity -> DTO
    }

    // READ ALL
    public List<PatientResponseDTO> getAllPatients() {
        return patientMapper.toResponseDTOList(patientRepository.findAll());
    }

    // READ ONE: Optional = "maybe a patient, maybe nothing"
    public Optional<PatientResponseDTO> getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toResponseDTO);
    }

    // UPDATE: empty Optional if the id does not exist
    public Optional<PatientResponseDTO> updatePatient(Long id, PatientRequestDTO request) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patientMapper.updateEntity(request, patient);       // copy new values
                    Patient updatedPatient = patientRepository.save(patient);
                    return patientMapper.toResponseDTO(updatedPatient);
                });
    }

    // DELETE: true if deleted, false if the id did not exist
    public boolean deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            return false;
        }
        patientRepository.deleteById(id);
        return true;
    }
}
