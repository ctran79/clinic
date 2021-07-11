package com.ctran79.clinic.backend.entity;

import org.hibernate.HibernateException;

/**
 * @author ctran79
 */

public abstract class BaseCrudService<E extends BaseEntity> {

    private BaseCrudRepository<E> repository;

    protected BaseCrudService(BaseCrudRepository<E> repository) {
        this.repository = repository;
    }

    public E getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new HibernateException("Object not found - id:" + id));
    }
}
