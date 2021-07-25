package com.ctran79.clinic.backend.entity;

import com.ctran79.clinic.backend.domain.ProductDto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author ctran79
 */

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product extends BaseEntity {
    @NotNull
    private String code;
    private String name;
    private String note;

    public Product toEntity(ProductDto dto) {
        this.setId(dto.getId());
        this.setCode(dto.getCode());
        this.setName(dto.getName());
        this.setNote(dto.getNote());
        return this;
    }
}
