package com.cstore.dao.image;

import com.cstore.model.product.Image;

import java.util.List;

public interface ImageDao {
    List<Image> findByProductId(Long productId);

    Image save(Image image);
}
