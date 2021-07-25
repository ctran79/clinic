package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.PagedSearchResultDto;
import com.ctran79.clinic.backend.domain.ProductDto;
import com.ctran79.clinic.backend.entity.BaseCrudService;
import com.ctran79.clinic.backend.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ctran79
 */

public abstract class BaseCrudFacade<E extends BaseEntity, D> {

    private BaseCrudService<E> service;

    protected BaseCrudFacade(BaseCrudService<E> service) {
        this.service = service;
    }

    public D getById(Long id) {
        E obj = service.getById(id);
        return toDto(obj);
    }

    public D createOrUpdateModel(D dto) {
        E entity = toEntity(dto);
        entity = service.createOrUpdateModel(entity);
        return toDto(entity);
    }

    public PagedSearchResultDto<D> search(Map<String, String> params) {
        Page<E> founded = service.search(params);
        List<D> content = founded.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        PagedSearchResultDto<D> pagedSearchResultDto = new PagedSearchResultDto<>();
        pagedSearchResultDto.setContent(content);
        pagedSearchResultDto.setTotalElements(founded.getTotalElements());
        pagedSearchResultDto.setTotalPages(founded.getTotalPages());

        return pagedSearchResultDto;
    }

    public abstract D toDto(E entity);

    public abstract E toEntity(D dto);

}
