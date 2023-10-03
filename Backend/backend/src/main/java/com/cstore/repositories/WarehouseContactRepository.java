package com.cstore.repositories;

import com.cstore.models.Warehouse;
import com.cstore.models.WarehouseContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseContactRepository extends JpaRepository<WarehouseContact, Integer> {
    List<WarehouseContact> findByWarehouse(Warehouse warehouse);
}
