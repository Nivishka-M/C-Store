package com.cstore.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_category", schema = "cstore", indexes = {@Index(name = "sub_category_id", columnList = "sub_category_id")})
public class SubCategory {
    @EmbeddedId
    private SubCategoryId id;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @MapsId("subCategoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private Category subCategory;

    public SubCategoryId getId() {
        return id;
    }

    public void setId(SubCategoryId id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Category subCategory) {
        this.subCategory = subCategory;
    }
}
