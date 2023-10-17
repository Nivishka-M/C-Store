package com.cstore.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name = "variant", schema = "cstore")
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
public class Variant {
    @Id @Column(name = "variant_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
        name = "variantIdGenerator", sequenceName = "variantIdSequence",
        allocationSize = 1, initialValue = 1
    )
    private Long variantId;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "weight", precision = 5, scale = 2)
    private BigDecimal weight;
}
