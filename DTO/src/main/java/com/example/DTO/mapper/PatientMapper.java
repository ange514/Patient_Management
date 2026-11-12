package com.example.DTO.Project.mapper;

import com.example.DTO.Project.dto.PatientRequestDTO;
import com.example.DTO.Project.dto.PatientResponseDTO;
import com.example.DTO.Project.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * AUTOMATED MAPPING with MapStruct.
 * MapStruct writes PatientMapperImpl for us when we compile:
 * target/generated-sources/annotations/com/example/DTO/Project/mapper/PatientMapperImpl.java
 *
 * componentModel = "spring"          -> the generated class is a Spring bean (injectable)
 * unmappedTargetPolicy = ERROR       -> a forgotten field stops the compile
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PatientMapper {

    // Request DTO -> new Entity
    @Mapping(target = "id", ignore = true)      // the database creates the id
    Patient toEntity(PatientRequestDTO request);

    // Entity -> Response DTO: same names are copied automatically;
    // nationalId and medicalNotes are not in the DTO, so they are never copied
    PatientResponseDTO toResponseDTO(Patient patient);

    // List of entities -> list of DTOs (reuses toResponseDTO)
    List<PatientResponseDTO> toResponseDTOList(List<Patient> patients);

    // Request DTO -> EXISTING Entity (for update)
    @Mapping(target = "id", ignore = true)      // never change the id
    void updateEntity(PatientRequestDTO request, @MappingTarget Patient patient);
}
