package com.cstore.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubCategoryId implements Serializable {
    private static final long serialVersionUID = -5125445457141907130L;
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "sub_category_id", nullable = false)
    private Long subCategoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubCategoryId entity = (SubCategoryId) o;
        return Objects.equals(this.subCategoryId, entity.subCategoryId) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategoryId, categoryId);
    }
}
