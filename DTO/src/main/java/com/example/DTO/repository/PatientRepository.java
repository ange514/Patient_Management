package com.example.DTO.Project.repository;

import com.example.DTO.Project.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {
}