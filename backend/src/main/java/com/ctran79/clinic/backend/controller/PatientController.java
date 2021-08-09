package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.patient.Patient;
import com.ctran79.clinic.backend.domain.patient.PatientDto;
import com.ctran79.clinic.backend.facade.BaseCrudFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ctran79
 */


@RestController
@RequestMapping(value = "/patient")
public class PatientController extends BaseCrudController<Patient, PatientDto>{
    protected PatientController(BaseCrudFacade<Patient, PatientDto> facade) {
        super(facade);
    }
}
