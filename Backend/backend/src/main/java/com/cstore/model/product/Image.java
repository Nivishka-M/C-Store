package com.cstore.model.product;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "image", schema = "cstore")
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
@ToString
public class Image {
    @Id @Column(name = "image_id", nullable = false)
    @GeneratedValue(generator = "imageIdGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
        name = "imageIdGenerator", sequenceName = "imageIdSequence",
        allocationSize = 1, initialValue = 1
    )
    private Long imageId;

    @Column(name = "url")
    private String url;
}
