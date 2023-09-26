package com.cstore.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_category")
public class SubCategory {
    @EmbeddedId
    private SubCategoryId id;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @MapsId("superCategoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "super_category_id", nullable = false)
    private Category superCategory;

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

    public Category getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }
}