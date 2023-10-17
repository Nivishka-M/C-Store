package com.cstore.domain.product.edit;

import com.cstore.dao.product.ProductDao;
import com.cstore.dao.image.ImageDao;
import com.cstore.dao.product.image.ProductImageDao;
import com.cstore.dto.NewProductDto;
import com.cstore.dto.ProductDto;
import com.cstore.model.product.Image;
import com.cstore.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductEditingService {
    private final ImageDao imageDao;
    private final ProductDao productDao;
    private final ProductImageDao productImageDao;

    public Long addBareProduct(ProductAddRequest toAdd) {
        Product product = Product
            .builder()
            .productName(toAdd.getProductName())
            .basePrice(toAdd.getBasePrice())
            .brand(toAdd.getBrand())
            .description(toAdd.getDescription())
            .imageUrl(toAdd.getMainImageUrl())
            .build();

        product = productDao.save(product);

        for (String imageUrl : toAdd.getOtherImageUrls()) {
            Image image = Image
                .builder()
                .url(imageUrl)
                .build();

            image = imageDao.save(image);

            productImageDao.save(image.getImageId(), product.getProductId());
        }

        return product.getProductId();
    }

    public ProductDto addNewProduct(NewProductDto product) {
        return null;
    }
}
