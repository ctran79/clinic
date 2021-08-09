package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.dictionary.Dictionary;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryDto;
import com.ctran79.clinic.backend.service.dictionary.DictionaryService;

/**
 * @author ctran79
 */

public class DictionaryFacade extends BaseCrudFacade<Dictionary, DictionaryDto>{
    private final DictionaryService dictionaryService;

    public DictionaryFacade(DictionaryService dictionaryService) {
        super(dictionaryService);
        this.dictionaryService = dictionaryService;
    }

    @Override
    public DictionaryDto toDto(Dictionary entity) {
        return entity.toDto();
    }

    @Override
    public Dictionary toEntity(DictionaryDto dto) {
        throw new UnsupportedOperationException();
    }
}
