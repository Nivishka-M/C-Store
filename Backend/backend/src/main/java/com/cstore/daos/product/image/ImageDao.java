package com.cstore.daos.product.image;

import com.cstore.models.Image;

import java.util.List;

public interface ImageDao {
    public List<Image> findByProductId(Long productId);
}
