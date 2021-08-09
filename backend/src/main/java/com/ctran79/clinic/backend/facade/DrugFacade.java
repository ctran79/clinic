package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.drug.Drug;
import com.ctran79.clinic.backend.domain.drug.DrugDto;
import com.ctran79.clinic.backend.service.drug.DrugService;

import java.util.Optional;

/** @author ctran79 */
public class DrugFacade extends BaseCrudFacade<Drug, DrugDto> {
  private final DrugService drugService;

  public DrugFacade(DrugService drugService) {
    super(drugService);
    this.drugService = drugService;
  }

  @Override
  public DrugDto toDto(Drug drug) {
    return drug.toDto();
  }

  @Override
  public Drug toEntity(DrugDto dto) {
    Drug drug =
        Optional.ofNullable(dto.getId()).map(drugService::getById).orElseGet(() -> new Drug());

    return drug.toEntity(dto);
  }
}
