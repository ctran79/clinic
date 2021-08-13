package com.ctran79.clinic.backend.specification;

import com.ctran79.clinic.backend.domain.patient.Patient;
import com.ctran79.clinic.backend.domain.patient.Patient_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** @author ctran79 */
public class PatientSpecification extends BaseSpecification<Patient> {
  public static final String CREATE_DATE_FROM = "createDateFrom";
  public static final String CREATE_DATE_TO = "createDateTo";
  public static final String ALL_PATIENTS = "allPatients";

  public static Specification buildSpecification(Map<String, String> params) {
    return (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.hasText(params.get(CREATE_DATE_FROM))) {
        LocalDateTime startOfDay =
            LocalDate.parse(params.get(CREATE_DATE_FROM), DATE_TIME_FORMATTER).atStartOfDay();
        predicates.add(
            criteriaBuilder.greaterThanOrEqualTo(root.get(Patient_.CREATE_DATE), startOfDay));
      }
      if (StringUtils.hasText(params.get(CREATE_DATE_TO))) {
        LocalDateTime endOfDay =
            LocalDate.parse(params.get(CREATE_DATE_TO), DATE_TIME_FORMATTER).atTime(23, 59, 59);
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Patient_.CREATE_DATE), endOfDay));
      }
      if (StringUtils.hasText(params.get(ALL_PATIENTS))) {
        Boolean allPatients = Boolean.parseBoolean(params.get(ALL_PATIENTS));
        if (allPatients) {
          predicates.add(
              criteriaBuilder.or(
                  criteriaBuilder.isTrue(root.get(Patient_.IS_EXAMINED)),
                  criteriaBuilder.isFalse(root.get(Patient_.IS_EXAMINED))));
        } else {
          predicates.add(criteriaBuilder.isFalse(root.get(Patient_.IS_EXAMINED)));
        }
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    };
  }
}
