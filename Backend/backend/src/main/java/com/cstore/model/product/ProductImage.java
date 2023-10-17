package com.cstore.model.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity @Table(name = "product_image", schema = "cstore")
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
public class ProductImage {
    @ToString.Include
    @Id @Column(name = "image_id", nullable = false)
    private Long id;

    @ToString.Exclude
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Product product;
}
