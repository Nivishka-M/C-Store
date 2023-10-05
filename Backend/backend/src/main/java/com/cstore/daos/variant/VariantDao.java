package com.cstore.daos.variant;

import com.cstore.models.Variant;

import java.sql.SQLException;
import java.util.Optional;

public interface VariantDao {
    Optional<Variant> findById(Long variantId) throws SQLException;
}
