package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.dictionary.Dictionary;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryDto;
import com.ctran79.clinic.backend.domain.drug.Drug;
import com.ctran79.clinic.backend.domain.drug.DrugDto;
import com.ctran79.clinic.backend.facade.BaseCrudFacade;
import com.ctran79.clinic.backend.facade.DictionaryFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author ctran79
 */

@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController extends BaseCrudController<Dictionary, DictionaryDto>{

    private final DictionaryFacade dictionaryFacade;

    protected DictionaryController(DictionaryFacade dictionaryFacade) {
        super(dictionaryFacade);
        this.dictionaryFacade = dictionaryFacade;
    }

    @GetMapping("/code/{code}")
    public DictionaryDto getDictByCode(@PathVariable String code) {
        return dictionaryFacade.getDictByCode(code);
    }
}
