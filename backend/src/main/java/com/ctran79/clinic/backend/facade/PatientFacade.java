package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.PagedSearchResultDto;
import com.ctran79.clinic.backend.domain.patient.Patient;
import com.ctran79.clinic.backend.domain.patient.PatientDto;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueService;
import com.ctran79.clinic.backend.service.patient.PatientService;
import com.ctran79.clinic.backend.service.prescription.PrescriptionRepository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/** @author ctran79 */
public class PatientFacade extends BaseCrudFacade<Patient, PatientDto> {

  private final PatientService patientService;
  private final DictionaryValueService dictionaryValueService;
  private final PrescriptionRepository prescriptionRepository;

  public PatientFacade(
      PatientService patientService,
      DictionaryValueService dictionaryValueService,
      PrescriptionRepository prescriptionRepository) {
    super(patientService);
    this.patientService = patientService;
    this.dictionaryValueService = dictionaryValueService;
    this.prescriptionRepository = prescriptionRepository;
  }

  @Override
  public PagedSearchResultDto<PatientDto> search(Map<String, String> params) {
    PagedSearchResultDto<PatientDto> page = super.search(params);
    if (page.getContent().size() > 0) {

      Set<Long> patientIds =
          prescriptionRepository.filterPatientHasPrescription(
              page.getContent().stream().map(PatientDto::getId).collect(Collectors.toSet()));

      Set<Long> finalPatientIds = patientIds;
      page.getContent()
          .forEach(
              patientDto ->
                  patientDto.setHasPrescription(finalPatientIds.contains(patientDto.getId())));
    }
    return page;
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
