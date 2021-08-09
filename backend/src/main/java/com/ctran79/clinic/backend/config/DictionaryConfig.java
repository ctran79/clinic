package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.facade.DictionaryFacade;
import com.ctran79.clinic.backend.service.dictionary.DictionaryRepository;
import com.ctran79.clinic.backend.service.dictionary.DictionaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author ctran79 */
@Configuration
public class DictionaryConfig {

  @Bean
  public DictionaryFacade productFacade(DictionaryRepository dictionaryRepository) {
    DictionaryService dictionaryService = new DictionaryService(dictionaryRepository);
    DictionaryFacade dictionaryFacade = new DictionaryFacade(dictionaryService);
    return dictionaryFacade;
  }
}
