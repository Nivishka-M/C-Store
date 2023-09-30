package com.cstore.backend.repositories;

import com.cstore.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value="SELECT * " +
                 "FROM cstore.product;", nativeQuery=true)
    List<Product> findAllProducts();

    @Modifying
    @Query(value="CREATE VIEW v AS " +
                    "SELECT sub_category_id " +
                    "FROM cstore.sub_category " +
                    "WHERE category_id = ?1;" +

                 "SELECT * " +
                 "FROM cstore.product " +
                 "WHERE product_id IN (SELECT product_id " +
                 "                     FROM cstore.belongs_to " +
                 "                     WHERE category_id = ?1) " +
                 "UNION " +
                 "SELECT * " +
                 "FROM cstore.product " +
                 "WHERE product_id IN (SELECT product_id " +
                 "                     FROM v LEFT OUTER JOIN cstore.belongs_to ON sub_category_id = category_id);", nativeQuery=true)
    List<Product> findAllProductsBelongingTo(Long categoryId);
}
