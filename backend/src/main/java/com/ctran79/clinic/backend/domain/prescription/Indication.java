package com.ctran79.clinic.backend.domain.prescription;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
import com.ctran79.clinic.backend.domain.drug.Drug;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** @author ctran79 */
@Entity
@Getter
@Setter
@Table(name = "indications")
public class Indication extends BaseEntity {

  @ManyToOne(optional = false)
  @JoinColumn(name = "prescription_id")
  private Prescription prescription;

  @ManyToOne(optional = false)
  @JoinColumn(name = "drug_id")
  private Drug drug;

  private Double quantity;

  @ManyToOne(optional = false)
  @JoinColumn(name = "unit_id")
  private DictionaryValue unit;

  private String usage;

  public IndicationDto toDto() {
    return IndicationDto.builder()
        .id(id)
        .drug(drug.toDto())
        .quantity(quantity)
        .unit(unit.toDto())
        .usage(usage)
        .build();
  }

  public Indication toEntity(IndicationDto dto) {
    quantity = dto.getQuantity();
    usage = dto.getUsage();
    return this;
  }
}
