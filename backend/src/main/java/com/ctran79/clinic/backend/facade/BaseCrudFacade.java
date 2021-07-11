package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.entity.BaseCrudService;
import com.ctran79.clinic.backend.entity.BaseEntity;

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

    public abstract D toDto(E entity);
}
