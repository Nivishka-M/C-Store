package com.cstore.dao.variant;

import com.cstore.model.product.Variant;

import java.util.List;
import java.util.Optional;

public interface VariantDao {
    Optional<Variant> findById(Long variantId);

    List<Variant> findByProperty(Long propertyId);

    Variant save(Variant variant);

    Integer countStocks(Long varaintId);
}
