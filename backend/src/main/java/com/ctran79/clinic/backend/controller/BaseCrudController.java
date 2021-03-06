package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.PagedSearchResultDto;
import com.ctran79.clinic.backend.domain.BaseEntity;
import com.ctran79.clinic.backend.facade.BaseCrudFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        D obj = facade.getById(id);
        return obj;
    }

    @GetMapping(path = "/all")
    public List<D> getAll() {
        List<D> list = facade.getAll();
        return list;
    }

    @PostMapping(path = "")
    public D createOrUpdateModel(@RequestBody @Validated D dto) {
        D obj = facade.createOrUpdateModel(dto);
        return obj;
    }

    @GetMapping(path = "/search")
    public PagedSearchResultDto<D> search(@RequestParam Map<String, String> params) {
        PagedSearchResultDto<D> founded = facade.search(params);
        return founded;
    }
}
