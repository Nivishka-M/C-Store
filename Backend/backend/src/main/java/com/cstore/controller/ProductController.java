package com.cstore.controller;

import com.cstore.dto.NewProductDto;
import com.cstore.dto.ProductDto;
import com.cstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<ProductDto> getAllProducts() throws SQLException {
        return productService.getAllProducts();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{product_id}")
    public ProductDto getProductById(@PathVariable(name = "product_id", required = true) Long productId) throws SQLException {
        return productService.getProductById(productId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/search/{product_name}")
    public List<ProductDto> getProductByName(@PathVariable(name = "product_name", required = true) String productName) throws SQLException {
        return productService.getProductByName(productName);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/products")
    public ProductDto addNewProduct(@RequestBody(required = true) NewProductDto product) throws SQLException {
        return productService.addNewProduct(product);
    }
}
