package com.cstore.backend.repositories;

import com.cstore.backend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value="SELECT * " +
                 "FROM cstore.category " +
                 "WHERE category_id IN (SELECT DISTINCT category_id " +
                                       "FROM cstore.sub_category);", nativeQuery=true)
    List<Category> findAllBaseCategories();

    @Query(value="SELECT * " +
                 "FROM cstore.category " +
                 "WHERE category_id IN (SELECT sub_category_id " +
                                       "FROM cstore.sub_category " +
                                       "WHERE category_id = ?1);", nativeQuery=true)
    List<Category> findAllSubCategories(Long baseCategoryId);
}
