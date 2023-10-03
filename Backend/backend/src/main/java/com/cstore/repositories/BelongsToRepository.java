package com.cstore.repositories;

import com.cstore.models.BelongsTo;
import com.cstore.models.BelongsToId;
import com.cstore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BelongsToRepository extends JpaRepository<BelongsTo, BelongsToId> {
    List<BelongsTo> findByCategory(Category category);
}
