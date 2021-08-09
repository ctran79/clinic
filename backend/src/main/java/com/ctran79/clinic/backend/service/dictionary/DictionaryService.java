package com.ctran79.clinic.backend.service.dictionary;

import com.ctran79.clinic.backend.domain.dictionary.Dictionary;
import com.ctran79.clinic.backend.service.BaseCrudService;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/** @author ctran79 */
public class DictionaryService extends BaseCrudService<Dictionary> {
  private final DictionaryRepository dictionaryRepository;

  public DictionaryService(DictionaryRepository dictionaryRepository) {
    super(dictionaryRepository);
    this.dictionaryRepository = dictionaryRepository;
  }

  @Override
  protected Specification buildSpecification(Map<String, String> params) {
    throw new UnsupportedOperationException();
  }
}