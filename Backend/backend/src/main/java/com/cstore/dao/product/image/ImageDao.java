package com.cstore.dao.product.image;

import com.cstore.model.product.Image;

import java.util.List;

public interface ImageDao {
    public List<Image> findByProductId(Long productId);
}
