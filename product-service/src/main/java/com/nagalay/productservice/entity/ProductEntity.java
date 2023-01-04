package com.nagalay.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_name")
    private String productName;


    @ManyToOne
    @JoinColumn(name = "brand_id",
    foreignKey = @ForeignKey(name = "product_brand_fk"))
    private BrandEntity brand;

    @Column(name = "brand_id", insertable = false, updatable = false)
    private Long brandId;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "sales_price")
    private Double salesPrice;

}
