package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.facade.DrugFacade;
import com.ctran79.clinic.backend.service.drug.DrugRepository;
import com.ctran79.clinic.backend.service.drug.DrugService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author ctran79 */
@Configuration
public class DrugConfig {

  @Bean
  public DrugFacade drugFacade(DrugRepository drugRepository) {
    DrugService drugService = new DrugService(drugRepository);
    DrugFacade drugFacade = new DrugFacade(drugService);
    return drugFacade;
  }
}
