package com.ctran79.clinic.backend.domain.prescription;

import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValueDto;
import com.ctran79.clinic.backend.domain.drug.DrugDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** @author ctran79 */
@Getter
@Setter
@Builder
public class IndicationDto {
  private Long id;
  private Integer seqNo;
  private DrugDto drug;
  private Double quantity;
  private DictionaryValueDto unit;
  private String usage;
}
