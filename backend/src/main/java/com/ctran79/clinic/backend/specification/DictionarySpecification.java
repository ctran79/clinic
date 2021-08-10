package com.ctran79.clinic.backend.specification;

import com.ctran79.clinic.backend.domain.dictionary.Dictionary;
import com.ctran79.clinic.backend.domain.drug.Drug_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** @author ctran79 */
public class DictionarySpecification extends BaseSpecification<Dictionary> {
  public static Specification buildSpecification(Map<String, String> params) {
    return (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasText(params.get(Drug_.NAME))) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get(Drug_.NAME)),
                "%" + params.get(Drug_.NAME).toLowerCase() + "%"));
      }
      if (StringUtils.hasText(params.get(Drug_.USAGE))) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get(Drug_.USAGE)),
                "%" + params.get(Drug_.USAGE).toLowerCase() + "%"));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    };
  }
}
