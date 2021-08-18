package com.ctran79.clinic.backend.domain.patient;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValueDto;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/** @author ctran79 */
@Getter
@Setter
@Builder
public class PatientDto {
  private Long id;
  private LocalDateTime createDate;
  private String name;
  private LocalDateTime birthday;
  private DictionaryValueDto gender;
  private String telephone;
  private String address;
  private Integer weight;
  private Integer height;
  private Boolean isExamined;
  private Boolean hasPrescription;
}
