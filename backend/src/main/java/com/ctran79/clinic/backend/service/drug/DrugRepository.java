package com.ctran79.clinic.backend.service.drug;

import com.ctran79.clinic.backend.domain.drug.Drug;
import com.ctran79.clinic.backend.service.BaseCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends BaseCrudRepository<Drug> {
}
