package com.cstore.daos.property;

import com.cstore.models.Property;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PropertyDao {
    public Optional<Property> findById(Long propertyId) throws SQLException;

    List<Property> findByProductId(Long productId);
}
