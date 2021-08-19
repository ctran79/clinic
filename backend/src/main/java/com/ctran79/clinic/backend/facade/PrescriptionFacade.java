package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.prescription.*;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueService;
import com.ctran79.clinic.backend.service.drug.DrugService;
import com.ctran79.clinic.backend.service.patient.PatientService;
import com.ctran79.clinic.backend.service.prescription.DiagnosisService;
import com.ctran79.clinic.backend.service.prescription.IndicationService;
import com.ctran79.clinic.backend.service.prescription.PrescriptionService;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/** @author ctran79 */
public class PrescriptionFacade extends BaseCrudFacade<Prescription, PrescriptionDto> {
  private final PrescriptionService prescriptionService;
  private final IndicationService indicationService;
  private final DiagnosisService diagnosisService;
  private final PatientService patientService;
  private final DrugService drugService;
  private final DictionaryValueService dictionaryValueService;
  private final ResourceLoader resourceLoader;

  public PrescriptionFacade(
      PrescriptionService prescriptionService,
      IndicationService indicationService,
      DiagnosisService diagnosisService,
      PatientService patientService,
      DrugService drugService,
      DictionaryValueService dictionaryValueService,
      ResourceLoader resourceLoader) {
    super(prescriptionService);
    this.prescriptionService = prescriptionService;
    this.indicationService = indicationService;
    this.diagnosisService = diagnosisService;
    this.patientService = patientService;
    this.drugService = drugService;
    this.dictionaryValueService = dictionaryValueService;
    this.resourceLoader = resourceLoader;
  }

  @Override
  public PrescriptionDto toDto(Prescription entity) {
    return entity.toDto();
  }

  @Override
  public Prescription toEntity(PrescriptionDto dto) {
    Prescription prescription =
        Optional.ofNullable(dto.getId())
            .map(prescriptionService::getById)
            .orElseGet(() -> new Prescription());

    prescription.setPatient(patientService.getById(dto.getPatient().getId()));

    prescription.getDiagnoses().clear();
    if (!CollectionUtils.isEmpty(dto.getDiagnoses())) {
      dto.getDiagnoses().stream()
          .forEach(diagnosis -> prescription.addDiagnosis(convertDiagnosisToEntity(diagnosis)));
    }

    prescription.getIndications().clear();
    if (!CollectionUtils.isEmpty(dto.getIndications())) {
      dto.getIndications().stream()
          .forEach(indication -> prescription.addIndication(convertIndicationToEntity(indication)));
    }
    return prescription.toEntity(dto);
  }

  private Diagnosis convertDiagnosisToEntity(DiagnosisDto dto) {
    Diagnosis diagnosis =
        Optional.ofNullable(dto.getId())
            .map(diagnosisService::getById)
            .orElseGet(() -> new Diagnosis());
    diagnosis.setDiagnosis(dictionaryValueService.getById(dto.getDiagnosis().getId()));
    return diagnosis.toEntity(dto);
  }

  private Indication convertIndicationToEntity(IndicationDto dto) {
    Indication indication =
        Optional.ofNullable(dto.getId())
            .map(indicationService::getById)
            .orElseGet(() -> new Indication());
    indication.setDrug(drugService.getById(dto.getDrug().getId()));
    indication.setUnit(dictionaryValueService.getById(dto.getUnit().getId()));
    return indication.toEntity(dto);
  }

  public PrescriptionDto getByPatientId(Long patientId) {
    Prescription prescription = prescriptionService.getByPatientId(patientId);
    return prescription == null ? null : toDto(prescription);
  }

  public JasperPrint getPrescriptionAsReport(Long patientId) throws JRException, IOException {
    Resource resource = resourceLoader.getResource("classpath:/reports/prescription.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());
    PrescriptionDto prescription = getByPatientId(patientId);

    Map<String, Object> params = new HashMap<>();
    params.put("patientName", prescription.getPatient().getName());
    params.put("patientAddress", prescription.getPatient().getAddress());
    params.put("patientGender", prescription.getPatient().getGender().getValue());
    params.put(
        "createDate",
        prescription
            .getPatient()
            .getCreateDate()
            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    List<DiagnosisDto> diagnoses =
        prescription.getDiagnoses().stream().collect(Collectors.toList());
    diagnoses.sort(Comparator.comparingInt(DiagnosisDto::getSeqNo));
    params.put("diagnoses", diagnoses);

    List<IndicationDto> indications =
        prescription.getIndications().stream().collect(Collectors.toList());
    indications.sort(Comparator.comparingLong(IndicationDto::getId));
    for (int i = 0; i < indications.size(); i++) {
      indications.get(i).setSeqNo(i + 1);
    }
    params.put("indications", indications);
    params.put("note", prescription.getNote());

    return JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
  }

  @Transactional
  @Override
  public PrescriptionDto createOrUpdateModel(PrescriptionDto dto) {
    Prescription prescription = toEntity(dto);
    prescription.getPatient().setIsExamined(true);
    prescription = prescriptionService.createOrUpdateModel(prescription);
    return toDto(prescription);
  }
}
