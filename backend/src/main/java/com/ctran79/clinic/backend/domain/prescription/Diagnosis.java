package com.ctran79.clinic.backend.domain.prescription;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
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
@Table(name = "diagnoses")
public class Diagnosis extends BaseEntity {
  @NotNull private Integer seqNo;

  @ManyToOne(optional = false)
  @JoinColumn(name = "prescription_id")
  private Prescription prescription;

  @ManyToOne(optional = false)
  @JoinColumn(name = "diagnosis_id")
  private DictionaryValue diagnosis;

  public DiagnosisDto toDto() {
    return DiagnosisDto.builder().id(id).seqNo(seqNo).diagnosis(diagnosis.toDto()).build();
  }

  public Diagnosis toEntity(DiagnosisDto dto) {
    seqNo = dto.getSeqNo();
    return this;
  }
}
