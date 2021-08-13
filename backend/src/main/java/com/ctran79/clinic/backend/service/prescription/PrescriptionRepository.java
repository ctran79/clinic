package com.ctran79.clinic.backend.service.prescription;

import com.ctran79.clinic.backend.domain.prescription.Prescription;
import com.ctran79.clinic.backend.service.BaseCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends BaseCrudRepository<Prescription> {
  Prescription getByPatientId(Long patientId);
}
