package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.patient.Patient;
import com.ctran79.clinic.backend.domain.patient.PatientDto;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueService;
import com.ctran79.clinic.backend.service.patient.PatientService;

import java.util.Optional;

/** @author ctran79 */
public class PatientFacade extends BaseCrudFacade<Patient, PatientDto> {

  private final PatientService patientService;
  private final DictionaryValueService dictionaryValueService;

  public PatientFacade(
      PatientService patientService, DictionaryValueService dictionaryValueService) {
    super(patientService);
    this.patientService = patientService;
    this.dictionaryValueService = dictionaryValueService;
  }

  @Override
  public PatientDto toDto(Patient entity) {
    return entity.toDto();
  }

  @Override
  public Patient toEntity(PatientDto dto) {
    Patient patient =
        Optional.ofNullable(dto.getId())
            .map(patientService::getById)
            .orElseGet(() -> new Patient());
    patient.setGender(dictionaryValueService.getById(dto.getGender().getId()));
    return patient.toEntity(dto);
  }
}
