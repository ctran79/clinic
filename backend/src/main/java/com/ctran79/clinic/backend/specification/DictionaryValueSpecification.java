package com.ctran79.clinic.backend.specification;

import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue;
import com.ctran79.clinic.backend.domain.dictionary.DictionaryValue_;
import com.ctran79.clinic.backend.domain.dictionary.Dictionary_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** @author ctran79 */
public class DictionaryValueSpecification extends BaseSpecification<DictionaryValue> {
  public static Specification buildSpecification(Map<String, String> params) {
    return (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasText(params.get(DictionaryValue_.DICTIONARY))) {
        predicates.add(
            criteriaBuilder.equal(
                root.get(DictionaryValue_.DICTIONARY).get(Dictionary_.CODE),
                params.get(DictionaryValue_.DICTIONARY)));
      }
      if (StringUtils.hasText(params.get(DictionaryValue_.VALUE))) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get(DictionaryValue_.VALUE)),
                "%" + params.get(DictionaryValue_.VALUE).toLowerCase() + "%"));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    };
  }
}
