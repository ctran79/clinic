package com.ctran79.clinic.backend.domain.dictionary;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** @author ctran79 */
@Entity
@Getter
@Setter
@Table(name = "dictionaries")
public class Dictionary extends BaseEntity {
  @NotNull private String code;
  @NotNull private String name;

  @OneToMany(
      fetch = FetchType.EAGER,
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      mappedBy = "dictionary")
  private List<DictionaryValue> dictionaryValues = new ArrayList<>();

  public DictionaryDto toDto() {
    return DictionaryDto.builder().id(id).code(code).name(name).build();
  }
}
