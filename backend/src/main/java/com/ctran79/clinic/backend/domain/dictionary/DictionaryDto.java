package com.ctran79.clinic.backend.domain.dictionary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/** @author ctran79 */
@Getter
@Setter
@Builder
public class DictionaryDto {
  private Long id;
  private String code;
  private String name;
  private List<DictionaryValueDto> dictionaryValues;
}
