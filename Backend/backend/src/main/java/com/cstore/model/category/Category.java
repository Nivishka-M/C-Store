package com.cstore.model.category;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category", schema = "cstore/public")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "category_name", length = 40)
    private String categoryName;

    @Lob
    @Column(name = "category_description")
    private String categoryDescription;
}
