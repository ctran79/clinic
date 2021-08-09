package com.ctran79.clinic.backend.service.patient;

import com.ctran79.clinic.backend.domain.patient.Patient;
import com.ctran79.clinic.backend.service.BaseCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends BaseCrudRepository<Patient> {}
