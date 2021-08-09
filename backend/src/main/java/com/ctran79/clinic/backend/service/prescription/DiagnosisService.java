package com.ctran79.clinic.backend.service.prescription;

import com.ctran79.clinic.backend.domain.prescription.Diagnosis;
import com.ctran79.clinic.backend.service.BaseCrudService;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/** @author ctran79 */
public class DiagnosisService extends BaseCrudService<Diagnosis> {
  private final DiagnosisRepository diagnosisRepository;

  public DiagnosisService(DiagnosisRepository diagnosisRepository) {
    super(diagnosisRepository);
    this.diagnosisRepository = diagnosisRepository;
  }

  @Override
  protected Specification buildSpecification(Map<String, String> params) {
    throw new UnsupportedOperationException();
  }
}
