package com.ctran79.clinic.backend.service;

import com.ctran79.clinic.backend.domain.BaseEntity;
import org.hibernate.HibernateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/** @author ctran79 */
public abstract class BaseCrudService<E extends BaseEntity> {

  public static final String PAGE_SIZE = "pageSize";
  public static final String PAGE_NUM = "pageNum";
  public static final String SORT = "sort";
  public static final String DIR = "dir";

  private BaseCrudRepository<E> repository;

  protected BaseCrudService(BaseCrudRepository<E> repository) {
    this.repository = repository;
  }

  public E getById(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new HibernateException("Object not found - id:" + id));
  }

  public E createOrUpdateModel(E entity) {
    return repository.save(entity);
  }

  public Page<E> search(Map<String, String> params) {
    Specification specification = buildSpecification(params);
    Pageable pageable = buildPageable(params);

    return repository.findAll(specification, pageable);
  }

  protected Pageable buildPageable(Map<String, String> params) {
    if (!params.containsKey(PAGE_SIZE)) {
      return Pageable.unpaged();
    }
    int pageSize = Integer.valueOf(params.get(PAGE_SIZE));
    int pageNum = 0;
    if (params.containsKey(PAGE_NUM)) {
      pageNum = Integer.valueOf(params.get(PAGE_NUM));
    }

    if (params.containsKey(SORT) && params.containsKey(DIR)) {
      Sort.Direction direction = Sort.Direction.fromString(params.get(DIR));
      return PageRequest.of(pageNum, pageSize, direction, params.get(SORT));
    } else {
      return PageRequest.of(pageNum, pageSize);
    }
  }

  protected abstract Specification buildSpecification(Map<String, String> params);

  public List<E> getAll() {
    return repository.findAll();
  }
}
