package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.dictionary.Dictionary;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryDto;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValueDto;
import com.ctran79.clinic.backend.domain.drug.Drug;
import com.ctran79.clinic.backend.domain.prescription.Diagnosis;
import com.ctran79.clinic.backend.service.dictionary.DictionaryService;
import com.ctran79.clinic.backend.service.dictionary.DictionaryValueService;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

/**
 * @author ctran79
 */

public class DictionaryFacade extends BaseCrudFacade<Dictionary, DictionaryDto>{
    private final DictionaryService dictionaryService;
    private final DictionaryValueService dictionaryValueService;

    public DictionaryFacade(DictionaryService dictionaryService, DictionaryValueService dictionaryValueService) {
        super(dictionaryService);
        this.dictionaryService = dictionaryService;
        this.dictionaryValueService = dictionaryValueService;
    }

    @Override
    public DictionaryDto toDto(Dictionary entity) {
        return entity.toDto();
    }

    @Override
    public Dictionary toEntity(DictionaryDto dto) {
        Dictionary dictionary = Optional.ofNullable(dto.getId()).map(dictionaryService::getById).orElseGet(() -> new Dictionary());
        dictionary.getDictionaryValues().clear();
        if (!CollectionUtils.isEmpty(dto.getDictionaryValues())) {
            dto.getDictionaryValues().stream()
                    .forEach(dictValue -> dictionary.addDictionaryValue(convertDictValueToEntity(dictValue)));
        }
        return dictionary.toEntity(dto);
    }

    private DictionaryValue convertDictValueToEntity(DictionaryValueDto dto) {
        DictionaryValue dictionaryValue =
                Optional.ofNullable(dto.getId())
                        .map(dictionaryValueService::getById)
                        .orElseGet(() -> new DictionaryValue());
        return dictionaryValue.toEntity(dto);
    }
}
