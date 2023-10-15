package com.cstore.dto;

import lombok.*;

@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSelectionCategory {
    private Long categoryId;
    private String categoryName;
}
