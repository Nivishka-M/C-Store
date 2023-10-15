package com.cstore.dao.varieson;

import com.cstore.model.product.Product;
import com.cstore.model.product.VariesOn;

import java.util.List;

public interface VariesOnDAO {
    List<VariesOn> findByProduct(Product product);
}
