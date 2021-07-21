package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.entity.BaseEntity;
import com.ctran79.clinic.backend.facade.BaseCrudFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ctran79
 */

public abstract class BaseCrudController<E extends BaseEntity, D> {

    private BaseCrudFacade<E, D> facade;

    protected BaseCrudController(BaseCrudFacade<E, D> facade) {
        this.facade = facade;
    }

    @GetMapping(path = "/{id}")
    public D getById(@PathVariable Long id) {
        return facade.getById(id);
    }

    @PostMapping(path = "")
    public D createModel(@RequestBody @Validated D dto) {
        return facade.createModel(dto);
    }
}
