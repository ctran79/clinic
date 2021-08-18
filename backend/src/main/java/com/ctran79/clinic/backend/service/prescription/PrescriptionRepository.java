package com.ctran79.clinic.backend.service.prescription;

import com.ctran79.clinic.backend.domain.prescription.Prescription;
import com.ctran79.clinic.backend.service.BaseCrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PrescriptionRepository extends BaseCrudRepository<Prescription> {
  Prescription getByPatientId(Long patientId);

  @Query("select patient.id from Prescription where patient.id in (:patientIds)")
  Set<Long> filterPatientHasPrescription(@Param("patientIds") Set<Long> patientIds);
}
