package com.cstore.repositories;

import com.cstore.dtos.WarehouseVariantDTO;
import com.cstore.models.Inventory;
import com.cstore.models.InventoryId;
import com.cstore.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
    List<Inventory> findByWarehouse(Warehouse warehouse);
}
