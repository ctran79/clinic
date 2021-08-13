package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.prescription.Prescription;
import com.ctran79.clinic.backend.domain.prescription.PrescriptionDto;
import com.ctran79.clinic.backend.facade.PrescriptionFacade;
import com.ctran79.clinic.backend.service.prescription.PrescriptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author ctran79 */
@RestController
@RequestMapping(value = "/prescription")
public class PrescriptionController extends BaseCrudController<Prescription, PrescriptionDto> {

  private final PrescriptionFacade prescriptionFacade;

  public PrescriptionController(PrescriptionFacade prescriptionFacade) {
    super(prescriptionFacade);
    this.prescriptionFacade = prescriptionFacade;
  }

  @GetMapping(path = "/patient/{patientId}")
  public PrescriptionDto getByPatientId(@PathVariable Long patientId) {
    return prescriptionFacade.getByPatientId(patientId);
  }
}
