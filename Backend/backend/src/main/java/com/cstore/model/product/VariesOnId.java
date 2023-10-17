package com.cstore.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class VariesOnId implements Serializable {
    @Serial
    private static final long serialVersionUID = 7792216859840206269L;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "property_id", nullable = false)
    private Long propertyId;

    @Column(name = "variant_id", nullable = false)
    private Long variantId;
}
