package com.cstore.controllers;

import com.cstore.dtos.WarehouseDTO;
import com.cstore.dtos.WarehouseVariantDTO;
import com.cstore.services.InventoryService;
import com.cstore.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/cstore/api/warehouses")
public class WarehouseController {
    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(InventoryService inventoryService, WarehouseService warehouseService) {
        this.inventoryService = inventoryService;
        this.warehouseService = warehouseService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @RequestMapping(method = RequestMethod.GET, path = "{warehouse_id}")
    public WarehouseDTO getWarehouseById(@PathVariable(name = "warehouse_id", required = true) Long warehouseId) {
        return warehouseService.getWarehouseById(warehouseId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{warehouse_id}/products")
    public List<WarehouseVariantDTO> checkInventory(@PathVariable(name = "warehouse_id", required = true) Long warehouseId) {
        return inventoryService.getWarehouseVariants(warehouseId);
    }
}
