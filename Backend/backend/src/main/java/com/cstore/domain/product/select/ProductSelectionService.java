package com.cstore.domain.product.select;

import com.cstore.dao.category.CategoryDao;
import com.cstore.dao.product.ProductDao;
import com.cstore.dao.product.image.ImageDao;
import com.cstore.dao.property.PropertyDao;
import com.cstore.dto.Product__;
import com.cstore.dto._Category;
import com.cstore.exception.NoSuchProductException;
import com.cstore.model.category.Category;
import com.cstore.model.product.Image;
import com.cstore.model.product.Product;
import com.cstore.model.product.Property;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSelectionService {
    private final CategoryDao categoryDao;
    private final ImageDao imageDao;
    private final ProductDao productDao;
    private final PropertyDao propertyDao;

    private Product__ convert(Product product, List<Image> images, List<Category> categories, List<Property> properties, Integer stockCount) {
        Product__ product__ = new Product__();

        product__.setProductId(product.getProductId());
        product__.setProductName(product.getProductName());
        product__.setBasePrice(product.getBasePrice());
        product__.setBrand(product.getBrand());
        product__.setDescription(product.getDescription());
        product__.setImageUrl(product.getImageUrl());

        product__.setOtherImages(images);

        List<_Category> _categories = new ArrayList<_Category>();
        for (Category category : categories) {
            _Category _category = new _Category();

            _category.setCategoryId(category.getCategoryId());
            _category.setCategoryName(category.getCategoryName());

            _categories.add(_category);
        }
        product__.setCategories(_categories);

        product__.setProperties(properties);

        product__.setStockCount(stockCount);

        return product__;
    }

    public ProductSelectionService(CategoryDao categoryDao, ImageDao imageDao, ProductDao productDao, PropertyDao propertyDao) {
        this.categoryDao = categoryDao;
        this.imageDao = imageDao;
        this.productDao = productDao;
        this.propertyDao = propertyDao;
    }

    public Product__ findById(Long productId) {
        Product product = new Product();
        Optional<Product> tempProduct = productDao.findById(productId);
        List<Category> categories = new ArrayList<>();
        List<Image> images = new ArrayList<>();
        List<Property> properties = new ArrayList<>();


        if (tempProduct.isEmpty()) {
            throw new NoSuchProductException("WholeProduct with id " + productId + " not found.");
        }
        product = tempProduct.get();

        images = imageDao.findByProductId(product.getProductId());

        categories = categoryDao.findByProductId(product.getProductId());

        properties = propertyDao.findByProductId(product.getProductId());

        return convert(product, images, categories, properties, productDao.countStocks(product.getProductId()));
    }
}
