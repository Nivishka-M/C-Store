package com.cstore.repositories;

import com.cstore.model.warehouse.Warehouse;
import com.cstore.model.warehouse.WarehouseContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseContactRepository extends JpaRepository<WarehouseContact, Integer> {
    List<WarehouseContact> findByWarehouse(Warehouse warehouse);
}
