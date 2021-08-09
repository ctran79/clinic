package com.ctran79.clinic.backend.domain.drug;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/** @author ctran79 */
@Getter
@Setter
@Builder
public class DrugDto extends BaseEntity {
  private Long id;
  private String name;
  private String usage;
}
