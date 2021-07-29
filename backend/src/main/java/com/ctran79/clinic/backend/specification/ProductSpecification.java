package com.ctran79.clinic.backend.specification;

import com.ctran79.clinic.backend.domain.product.Product_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ctran79
 */

public class ProductSpecification extends BaseSpecification {
    public static Specification buildSpecification(Map<String, String> params) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(params.get(Product_.CODE))) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(Product_.CODE)), "%" + params.get(Product_.CODE).toLowerCase() + "%"));
            }
            if (StringUtils.hasText(params.get(Product_.NAME))) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(Product_.NAME)), "%" + params.get(Product_.NAME).toLowerCase() + "%"));
            }
            if (StringUtils.hasText(params.get(Product_.NOTE))) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(Product_.NOTE)), "%" + params.get(Product_.NOTE).toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
