package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.facade.PatientFacade;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueRepository;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueService;
import com.ctran79.clinic.backend.service.patient.PatientRepository;
import com.ctran79.clinic.backend.service.patient.PatientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author ctran79 */
@Configuration
public class PatientConfig {

  @Bean
  public PatientFacade patientFacade(PatientRepository patientRepository, DictionaryValueRepository dictionaryValueRepository) {
    PatientService patientService = new PatientService(patientRepository);
    DictionaryValueService dictionaryValueService = new DictionaryValueService(dictionaryValueRepository);
    PatientFacade patientFacade = new PatientFacade(patientService, dictionaryValueService);
    return patientFacade;
  }
}
