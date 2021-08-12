package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.facade.DictionaryFacade;
import com.ctran79.clinic.backend.service.dictionary.DictionaryRepository;
import com.ctran79.clinic.backend.service.dictionary.DictionaryService;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueRepository;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author ctran79 */
@Configuration
public class DictionaryConfig {

  @Bean
  public DictionaryFacade productFacade(DictionaryRepository dictionaryRepository, DictionaryValueRepository dictionaryValueRepository) {
    DictionaryService dictionaryService = new DictionaryService(dictionaryRepository);
    DictionaryValueService dictionaryValueService = new DictionaryValueService(dictionaryValueRepository);
    DictionaryFacade dictionaryFacade = new DictionaryFacade(dictionaryService, dictionaryValueService);
    return dictionaryFacade;
  }
}
