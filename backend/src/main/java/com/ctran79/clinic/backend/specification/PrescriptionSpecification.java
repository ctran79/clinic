package com.ctran79.clinic.backend.specification;

import com.ctran79.clinic.backend.domain.prescription.Prescription;
import com.ctran79.clinic.backend.domain.prescription.Prescription_;
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
public class PrescriptionSpecification extends BaseSpecification<Prescription> {
  public static final String CREATE_DATE_FROM = "createDateFrom";
  public static final String CREATE_DATE_TO = "createDateTo";
  public static final String CLIENT = "client";
  public static final String ADDRESS = "address";

  public static Specification buildSpecification(Map<String, String> params) {
    return (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (StringUtils.hasText(params.get(CREATE_DATE_FROM))) {
        LocalDateTime startOfDay =
            LocalDate.parse(params.get(CREATE_DATE_FROM), DATE_TIME_FORMATTER).atStartOfDay();
        predicates.add(
            criteriaBuilder.greaterThanOrEqualTo(root.get(Prescription_.CREATE_DATE), startOfDay));
      }
      if (StringUtils.hasText(params.get(CREATE_DATE_TO))) {
        LocalDateTime endOfDay =
            LocalDate.parse(params.get(CREATE_DATE_TO), DATE_TIME_FORMATTER).atTime(23, 59, 59);
        predicates.add(
            criteriaBuilder.lessThanOrEqualTo(root.get(Prescription_.CREATE_DATE), endOfDay));
      }
      if (StringUtils.hasText(params.get(CLIENT))) {
        String client = params.get(CLIENT);
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get(Prescription_.PATIENT).get(Patient_.NAME)),
                "%" + client + "%"));
      }
      if (StringUtils.hasText(params.get(ADDRESS))) {
        String address = params.get(ADDRESS);
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get(Prescription_.PATIENT).get(Patient_.ADDRESS)),
                "%" + address + "%"));
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    };
  }
}
