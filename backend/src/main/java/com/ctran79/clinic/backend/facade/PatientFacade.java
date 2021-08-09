package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.drug.Drug;
import com.ctran79.clinic.backend.domain.drug.DrugDto;
import com.ctran79.clinic.backend.domain.patient.Patient;
import com.ctran79.clinic.backend.domain.patient.PatientDto;
import com.ctran79.clinic.backend.service.BaseCrudService;
import com.ctran79.clinic.backend.service.patient.PatientService;

/**
 * @author ctran79
 */

public class PatientFacade extends BaseCrudFacade<Patient, PatientDto>{

    public PatientFacade(PatientService service) {
        super(service);
    }

    @Override
    public PatientDto toDto(Patient entity) {
        return null;
    }

    @Override
    public Patient toEntity(PatientDto dto) {
        return null;
    }
}
