package com.ctran79.clinic.backend.domain.patient;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/** @author ctran79 */
@Entity
@Getter
@Setter
@Table(name = "patients")
public class Patient extends BaseEntity {
  @NotNull private String name;

  private LocalDateTime birthday;

  @NotNull
  @JoinColumn(name = "gender_id")
  @ManyToOne
  private DictionaryValue gender;

  private String telephone;
  private String address;
  private Integer weight;
  private Integer height;
  private Boolean isExamined;

  public PatientDto toDto() {
    return PatientDto.builder()
        .id(id)
        .createDate(createDate)
        .name(name)
        .birthday(birthday)
        .gender(gender.toDto())
        .telephone(telephone)
        .address(address)
        .weight(weight)
        .height(height)
        .isExamined(isExamined)
        .build();
  }

  public Patient toEntity(PatientDto dto) {
    name = dto.getName();
    birthday = dto.getBirthday();
    telephone = dto.getTelephone();
    address = dto.getAddress();
    weight = dto.getWeight();
    height = dto.getHeight();
    isExamined = dto.getIsExamined();
    return this;
  }
}
