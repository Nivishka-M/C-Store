package com.cstore.dao.property;

import com.cstore.model.product.Property;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PropertyDao {
    public Optional<Property> findById(Long propertyId);

    List<Property> findByProductId(Long productId);

    Property save(Property property);
}
