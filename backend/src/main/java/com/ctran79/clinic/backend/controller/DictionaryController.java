package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.dictionary.Dictionary;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryDto;
import com.ctran79.clinic.backend.domain.drug.Drug;
import com.ctran79.clinic.backend.domain.drug.DrugDto;
import com.ctran79.clinic.backend.facade.BaseCrudFacade;
import com.ctran79.clinic.backend.facade.DictionaryFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ctran79
 */

@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController extends BaseCrudController<Dictionary, DictionaryDto>{

    protected DictionaryController(DictionaryFacade dictionaryFacade) {
        super(dictionaryFacade);
    }
}
