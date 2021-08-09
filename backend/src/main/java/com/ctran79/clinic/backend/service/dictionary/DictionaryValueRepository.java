package com.ctran79.clinic.backend.service.dictionary;

import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
import com.ctran79.clinic.backend.service.BaseCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ctran79
 */

@Repository
public interface DictionaryValueRepository extends BaseCrudRepository<DictionaryValue> {
}
