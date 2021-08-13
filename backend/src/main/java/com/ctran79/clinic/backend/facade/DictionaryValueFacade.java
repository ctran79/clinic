package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.dictionary.*;
import com.ctran79.clinic.backend.service.dictionary.DictionaryService;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueService;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

/**
 * @author ctran79
 */

public class DictionaryValueFacade extends BaseCrudFacade<DictionaryValue, DictionaryValueDto>{
     private final DictionaryValueService dictionaryValueService;

    public DictionaryValueFacade(  DictionaryValueService dictionaryValueService) {
        super(dictionaryValueService);
         this.dictionaryValueService = dictionaryValueService;
    }

    @Override
    public DictionaryValueDto toDto(DictionaryValue entity) {
        return entity.toDto();
    }

    @Override
    public DictionaryValue toEntity(DictionaryValueDto dto) {
        throw new UnsupportedOperationException();
    }
}
