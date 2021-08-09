package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.prescription.Prescription;
import com.ctran79.clinic.backend.domain.prescription.PrescriptionDto;
import com.ctran79.clinic.backend.facade.PrescriptionFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author ctran79 */
@RestController
@RequestMapping(value = "/prescription")
public class PrescriptionController extends BaseCrudController<Prescription, PrescriptionDto> {

  public PrescriptionController(PrescriptionFacade prescriptionFacade) {
    super(prescriptionFacade);
  }
}
