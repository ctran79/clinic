package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.facade.PrescriptionFacade;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueRepository;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueService;
import com.ctran79.clinic.backend.service.drug.DrugRepository;
import com.ctran79.clinic.backend.service.drug.DrugService;
import com.ctran79.clinic.backend.service.patient.PatientRepository;
import com.ctran79.clinic.backend.service.patient.PatientService;
import com.ctran79.clinic.backend.service.prescription.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/** @author ctran79 */
@Configuration
public class PrescriptionConfig {

  @Bean
  public PrescriptionFacade orderFacade(
      PrescriptionRepository prescriptionRepository,
      IndicationRepository indicationRepository,
      DiagnosisRepository diagnosisRepository,
      PatientRepository patientRepository,
      DrugRepository drugRepository,
      DictionaryValueRepository dictionaryValueRepository,
      ResourceLoader resourceLoader) {

    PrescriptionService prescriptionService = new PrescriptionService(prescriptionRepository);
    IndicationService indicationService = new IndicationService(indicationRepository);
    DiagnosisService diagnosisService = new DiagnosisService(diagnosisRepository);
    PatientService patientService = new PatientService(patientRepository);
    DrugService drugService = new DrugService(drugRepository);
    DictionaryValueService dictionaryValueService =
        new DictionaryValueService(dictionaryValueRepository);

    PrescriptionFacade prescriptionFacade =
        new PrescriptionFacade(
            prescriptionService,
            indicationService,
            diagnosisService,
            patientService,
            drugService,
            dictionaryValueService,
            resourceLoader);
    return prescriptionFacade;
  }
}
