package com.ctran79.clinic.backend.service.drug;

import com.ctran79.clinic.backend.domain.drug.Drug;
import com.ctran79.clinic.backend.service.BaseCrudService;
import com.ctran79.clinic.backend.specification.DrugSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * @author ctran79
 */

public class DrugService extends BaseCrudService<Drug> {
    private final DrugRepository drugRepository;

    public DrugService(DrugRepository drugRepository) {
        super(drugRepository);
        this.drugRepository = drugRepository;
    }

    @Override
    protected Specification buildSpecification(Map<String, String> params) {
        return DrugSpecification.buildSpecification(params);
    }
}
