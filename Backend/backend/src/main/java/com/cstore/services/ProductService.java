package com.cstore.services;

import com.cstore.daos.product.ProductDAO;
import com.cstore.dtos.ProductDTO;
import com.cstore.dtos.PropertyDTO;
import com.cstore.exceptions.ProductNotFoundException;
import com.cstore.models.Product;
import com.cstore.models.VariesOn;
import com.cstore.repositories.VariesOnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductDAO productDAO;
    private final VariesOnRepository variesOnRepository;

    @Autowired
    public ProductService(ProductDAO productDAO, VariesOnRepository variesOnRepository) {
        this.productDAO = productDAO;
        this.variesOnRepository = variesOnRepository;
    }

    private ProductDTO convert(Product product) {
        List<VariesOn> variances = variesOnRepository.findByProduct(product);
        ProductDTO productDTO = new ProductDTO();
        List<PropertyDTO> properties = new ArrayList<PropertyDTO>();

        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setBasePrice(product.getBasePrice());
        productDTO.setBrand(product.getBrand());
        productDTO.setDescription(product.getDescription());
        productDTO.setMainImage(product.getMainImage());

        for (VariesOn variesOn : variances) {
            PropertyDTO propertyDTO = new PropertyDTO();

            propertyDTO.setPropertyName(variesOn.getProperty().getPropertyName());
            propertyDTO.setValue(variesOn.getProperty().getValue());
            propertyDTO.setImage(variesOn.getProperty().getImage());
            propertyDTO.setPriceIncrement(variesOn.getProperty().getPriceIncrement());

            properties.add(propertyDTO);
        }
        productDTO.setProperties(properties);

        return productDTO;
    }

    public List<ProductDTO> getAllProducts() throws SQLException {
        return productDAO.getAllProducts()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long productId) throws SQLException {
        Optional<Product> product = productDAO.getProductById(productId);

        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + productId + " not found.");
        }
        return convert(product.get());
    }
}
