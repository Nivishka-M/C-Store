package com.cstore.services;

import com.cstore.daos.product.ProductDAO;
import com.cstore.daos.varieson.VariesOnDao;
import com.cstore.dtos.NewProductDto;
import com.cstore.dtos.ProductDto;
import com.cstore.dtos.PropertyDto;
import com.cstore.exceptions.ProductNotFoundException;
import com.cstore.models.Product;
import com.cstore.models.VariesOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductDAO productDao;
    private final VariesOnDao variesOnDao;

    @Autowired
    public ProductService(ProductDAO productDao, VariesOnDao variesOnDao) {
        this.productDao = productDao;
        this.variesOnDao = variesOnDao;
    }

    private Product getProduct(Product unknown) throws SQLException {
        Optional<Product> product = productDao.findProduct(unknown);

        return product.orElse(null);
    }

    private ProductDto convert(Product product) {
        List<VariesOn> variances = variesOnDao.findByProduct(product);
        ProductDto productDTO = new ProductDto();
        List<PropertyDto> properties = new ArrayList<PropertyDto>();

        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setBasePrice(product.getBasePrice());
        productDTO.setBrand(product.getBrand());
        productDTO.setDescription(product.getDescription());
        productDTO.setMainImage(product.getMainImage());

        for (VariesOn variesOn : variances) {
            PropertyDto propertyDTO = new PropertyDto();

            propertyDTO.setPropertyId(variesOn.getProperty().getPropertyId());
            propertyDTO.setPropertyName(variesOn.getProperty().getPropertyName());
            propertyDTO.setValue(variesOn.getProperty().getValue());
            propertyDTO.setImage(variesOn.getProperty().getImage());
            propertyDTO.setPriceIncrement(variesOn.getProperty().getPriceIncrement());

            properties.add(propertyDTO);
        }
        productDTO.setProperties(properties);

        return productDTO;
    }

    public List<ProductDto> getAllProducts() throws SQLException {
        return productDao.findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

    }

    public ProductDto getProductById(Long productId) throws SQLException {
        Optional<Product> product = productDao.findById(productId);

        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + productId + " not found.");
        }
        return convert(product.get());
    }

    public List<ProductDto> getProductByName(String productName) throws SQLException {
        return productDao.findByName(productName)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public ProductDto addNewProduct(NewProductDto newProduct) throws SQLException {
        Product product = new Product();

        product.setProductName(newProduct.getProductName());
        product.setBasePrice(newProduct.getBasePrice());
        product.setBrand(newProduct.getBrand());
        product.setDescription(newProduct.getDescription());
        product.setMainImage(newProduct.getMainImage());

        productDao.save(product);
        product.setProductId(getProduct(product).getProductId());
        return null;
    }
}
