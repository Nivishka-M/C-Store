package com.cstore.repositories;

import com.cstore.models.Product;
import com.cstore.models.VariesOn;
import com.cstore.models.VariesOnId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariesOnRepository extends JpaRepository<VariesOn, VariesOnId> {
    List<VariesOn> findByProduct(Product product);
}
