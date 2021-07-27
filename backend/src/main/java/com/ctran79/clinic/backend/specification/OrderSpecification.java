package com.ctran79.clinic.backend.specification;

import com.ctran79.clinic.backend.entity.Order_;
import com.ctran79.clinic.backend.entity.Product_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ctran79
 */

public class OrderSpecification extends BaseSpecification {
    public static final String CREATE_DATE_FROM = "createDateFrom";
    public static final String CREATE_DATE_TO = "createDateTo";

    public static Specification buildSpecification(Map<String, String> params) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(params.get(CREATE_DATE_FROM))) {
                LocalDateTime startOfDay = LocalDate.parse(params.get(CREATE_DATE_FROM)).atStartOfDay();

                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Order_.CREATE_DATE), startOfDay));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
