package com.cstore.daos.varieson;

import com.cstore.models.Product;
import com.cstore.models.VariesOn;

import java.util.List;

public interface VariesOnDao {
    List<VariesOn> findByProduct(Product product);
}
