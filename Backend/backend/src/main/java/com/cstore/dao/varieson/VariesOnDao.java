package com.cstore.dao.varieson;

import com.cstore.model.product.Product;
import com.cstore.model.product.VariesOn;
import com.cstore.model.product.VariesOnId;

import java.util.List;

public interface VariesOnDao {
    List<VariesOn> findByProduct(Product product);

    VariesOnId save(VariesOnId variesOnId);
}
