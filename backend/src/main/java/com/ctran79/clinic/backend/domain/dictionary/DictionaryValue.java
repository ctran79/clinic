package com.ctran79.clinic.backend.domain.dictionary;

import com.ctran79.clinic.backend.domain.BaseEntity;
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
@Table(name = "dictionary_values")
public class DictionaryValue extends BaseEntity {
  @NotNull private String code;
  @NotNull private String value;

  @NotNull
  @JoinColumn(name = "dictionary_id")
  @ManyToOne
  private Dictionary dictionary;

  public DictionaryValueDto toDto() {
    return DictionaryValueDto.builder().id(id).code(code).value(value).build();
  }
}
