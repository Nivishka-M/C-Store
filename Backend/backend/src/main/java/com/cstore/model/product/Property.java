package com.cstore.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "property", schema = "cstore")
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
        name = "propertyIdGenerator", sequenceName = "propertyIdSequence",
        allocationSize = 1, initialValue = 1
    )
    @Column(name = "property_id", nullable = false)
    private Long propertyId;

    @Column(name = "property_name", length = 40)
    private String propertyName;

    @Column(name = "value", length = 40)
    private String value;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price_increment", precision = 10, scale = 2)
    private BigDecimal priceIncrement;
}
