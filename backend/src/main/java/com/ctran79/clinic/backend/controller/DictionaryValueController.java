package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.dictionary.Dictionary;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryDto;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValueDto;
import com.ctran79.clinic.backend.facade.DictionaryFacade;
import com.ctran79.clinic.backend.facade.DictionaryValueFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ctran79
 */

@RestController
@RequestMapping(value = "/dictionary-value")
public class DictionaryValueController extends BaseCrudController<DictionaryValue, DictionaryValueDto>{

    private final DictionaryValueFacade dictionaryValueFacade;

    protected DictionaryValueController(DictionaryValueFacade dictionaryValueFacade) {
        super(dictionaryValueFacade);
        this.dictionaryValueFacade = dictionaryValueFacade;
    }
}
