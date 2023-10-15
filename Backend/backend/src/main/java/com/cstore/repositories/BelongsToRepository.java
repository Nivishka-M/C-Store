package com.cstore.repositories;

import com.cstore.model.category.BelongsTo;
import com.cstore.model.category.BelongsToId;
import com.cstore.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BelongsToRepository extends JpaRepository<BelongsTo, BelongsToId> {
    List<BelongsTo> findByCategory(Category category);
}
