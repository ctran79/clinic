package com.ctran79.clinic.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ctran79
 */

@Setter
@Getter
public class PagedSearchResultDto<D> {
    private List<D> content;
    private Long totalElements;
    private Integer totalPages;
}
