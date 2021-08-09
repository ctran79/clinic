package com.ctran79.clinic.backend.domain.drug;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/** @author ctran79 */
@Entity
@Getter
@Setter
@Table(name = "drugs")
public class Drug extends BaseEntity {
  @NotNull private String name;
  private String usage;

  public DrugDto toDto() {
    return DrugDto.builder().id(id).name(name).usage(usage).build();
  }

  public Drug toEntity(DrugDto dto) {
    name =  dto.getName();
    usage = dto.getUsage();
    return this;
  }
}
