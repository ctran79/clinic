package com.ctran79.clinic.backend.service.patient;

import com.ctran79.clinic.backend.domain.patient.Patient;
import com.ctran79.clinic.backend.service.BaseCrudService;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/** @author ctran79 */
public class PatientService extends BaseCrudService<Patient> {
  private final PatientRepository patientRepository;

  public PatientService(PatientRepository patientRepository) {
    super(patientRepository);
    this.patientRepository = patientRepository;
  }

  @Override
  protected Specification buildSpecification(Map<String, String> params) {
    throw new UnsupportedOperationException();
  }
}
