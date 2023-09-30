package com.cstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InventoryId implements Serializable {
    private static final long serialVersionUID = 1439924339189163628L;
    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InventoryId entity = (InventoryId) o;
        return Objects.equals(this.warehouseId, entity.warehouseId) &&
                Objects.equals(this.variantId, entity.variantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseId, variantId);
    }
}
