package com.ctran79.clinic.backend.service.prescription;

import com.ctran79.clinic.backend.domain.prescription.Diagnosis;
import com.ctran79.clinic.backend.service.BaseCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends BaseCrudRepository<Diagnosis> {}
