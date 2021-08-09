package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.drug.Drug;
import com.ctran79.clinic.backend.domain.drug.DrugDto;
import com.ctran79.clinic.backend.facade.DrugFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author ctran79 */
@RestController
@RequestMapping(value = "/drug")
public class DrugController extends BaseCrudController<Drug, DrugDto> {

  public DrugController(DrugFacade drugFacade) {
    super(drugFacade);
  }
}
