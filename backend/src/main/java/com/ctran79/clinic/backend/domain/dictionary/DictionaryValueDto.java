package com.ctran79.clinic.backend.domain.dictionary;

import com.ctran79.clinic.backend.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** @author ctran79 */
@Getter
@Setter
@Builder
public class DictionaryValueDto extends BaseEntity {
  private Long id;
  private String code;
  private String value;
}
