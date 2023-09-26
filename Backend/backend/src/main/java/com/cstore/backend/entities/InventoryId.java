package com.cstore.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InventoryId implements Serializable {
    private static final long serialVersionUID = -1666668776138562580L;
    @Column(name = "warehouse_id", nullable = false)
    private int warehouseId;

    @Column(name = "variant_id", nullable = false)
    private int variantId;

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
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