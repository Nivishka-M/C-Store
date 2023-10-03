package com.cstore.controllers;

import com.cstore.dtos.ProductDTO;
import com.cstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/cstore/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<ProductDTO> getAllProducts() throws SQLException {
        return productService.getAllProducts();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{product_id}")
    public ProductDTO getProductById(@PathVariable(name = "product_id", required = true) Long productId) throws SQLException {
        return productService.getProductById(productId);
    }
}
