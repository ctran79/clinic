package com.ctran79.clinic.backend.domain.prescription;

import com.ctran79.clinic.backend.domain.patient.PatientDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/** @author ctran79 */
@Getter
@Setter
@Builder
public class PrescriptionDto {
  private Long id;
  private LocalDateTime createDate;
  private PatientDto patient;

  private Set<DiagnosisDto> diagnoses;
  private Set<IndicationDto> indications;
}
