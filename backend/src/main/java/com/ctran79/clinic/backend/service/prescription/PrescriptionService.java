package com.ctran79.clinic.backend.service.prescription;

import com.ctran79.clinic.backend.domain.prescription.Prescription;
import com.ctran79.clinic.backend.service.BaseCrudService;
import com.ctran79.clinic.backend.specification.PrescriptionSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/** @author ctran79 */
public class PrescriptionService extends BaseCrudService<Prescription> {
  private final PrescriptionRepository prescriptionRepository;

  public PrescriptionService(PrescriptionRepository prescriptionRepository) {
    super(prescriptionRepository);
    this.prescriptionRepository = prescriptionRepository;
  }

  @Override
  protected Specification buildSpecification(Map<String, String> params) {
    return PrescriptionSpecification.buildSpecification(params);
  }

  public Prescription getByPatientId(Long patientId) {
    return prescriptionRepository.getByPatientId(patientId);
  }
}
