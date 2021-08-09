package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.facade.DrugFacade;
import com.ctran79.clinic.backend.facade.PatientFacade;
import com.ctran79.clinic.backend.service.drug.DrugRepository;
import com.ctran79.clinic.backend.service.drug.DrugService;
import com.ctran79.clinic.backend.service.patient.PatientRepository;
import com.ctran79.clinic.backend.service.patient.PatientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author ctran79 */
@Configuration
public class PatientConfig {

  @Bean
  public PatientFacade patientFacade(PatientRepository patientRepository) {
    PatientService patientService = new PatientService(patientRepository);
    PatientFacade patientFacade = new PatientFacade(patientService);
    return patientFacade;
  }
}
