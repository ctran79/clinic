package com.ctran79.clinic.backend.service.prescription;

import com.ctran79.clinic.backend.domain.prescription.Indication;
import com.ctran79.clinic.backend.service.BaseCrudService;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/** @author ctran79 */
public class IndicationService extends BaseCrudService<Indication> {
  private final IndicationRepository indicationRepository;

  public IndicationService(IndicationRepository indicationRepository) {
    super(indicationRepository);
    this.indicationRepository = indicationRepository;
  }

  @Override
  protected Specification buildSpecification(Map<String, String> params) {
    throw new UnsupportedOperationException();
  }
}
