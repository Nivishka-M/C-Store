package com.cstore.repository;

import com.cstore.model.warehouse.Inventory;
import com.cstore.model.warehouse.InventoryId;
import com.cstore.model.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
    List<Inventory> findByWarehouse(Warehouse warehouse);
}
