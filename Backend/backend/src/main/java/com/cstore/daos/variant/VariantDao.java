package com.cstore.daos.variant;

import com.cstore.models.Variant;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface VariantDao {
    Optional<Variant> findById(Long variantId);

    List<Variant> findByProperty(Long propertyId);

    Integer countStocks(Long varaintId);
}
