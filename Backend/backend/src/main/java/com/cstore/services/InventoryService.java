package com.cstore.services;

import com.cstore.dtos.WarehouseVariantDTO;
import com.cstore.exceptions.WarehouseNotFoundException;
import com.cstore.models.Inventory;
import com.cstore.models.Warehouse;
import com.cstore.repositories.InventoryRepository;
import com.cstore.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, WarehouseRepository warehouseRepository) {
        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
    }

    private WarehouseVariantDTO convert(Inventory inventory) {
        WarehouseVariantDTO warehouseVariantDTO = new WarehouseVariantDTO();

        warehouseVariantDTO.setVariantId(inventory.getInventoryId().getVariantId());
        warehouseVariantDTO.setSku(inventory.getSku());
        warehouseVariantDTO.setCount(inventory.getCount());

        return warehouseVariantDTO;
    }
    public List<WarehouseVariantDTO> getWarehouseVariants(Long warehouseId) {
        Optional<Warehouse> tempWarehouse = warehouseRepository.findById(warehouseId);

        if (tempWarehouse.isEmpty()) {
            throw new WarehouseNotFoundException("Warehouse with id " + warehouseId + " does not exist.");
        }
        Warehouse warehouse = tempWarehouse.get();

        return inventoryRepository.findByWarehouse(warehouse)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
