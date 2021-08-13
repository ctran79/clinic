package com.ctran79.clinic.backend.service.dictionary;

import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
import com.ctran79.clinic.backend.service.BaseCrudService;
import com.ctran79.clinic.backend.specification.DictionaryValueSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/** @author ctran79 */
public class DictionaryValueService extends BaseCrudService<DictionaryValue> {
  private final DictionaryValueRepository dictionaryValueRepository;

  public DictionaryValueService(DictionaryValueRepository dictionaryValueRepository) {
    super(dictionaryValueRepository);
    this.dictionaryValueRepository = dictionaryValueRepository;
  }

  @Override
  protected Specification buildSpecification(Map<String, String> params) {
    return DictionaryValueSpecification.buildSpecification(params);
  }
}
